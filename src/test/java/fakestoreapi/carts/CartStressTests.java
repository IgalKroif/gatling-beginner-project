package fakestoreapi.carts;


import fakestoreapi.feeders.FeederImplementation;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.rampUsers;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static utils.chainers.ChainCartRequests.*;


public class CartStressTests extends Simulation implements FeederImplementation {

    // Define the HTTP protocol to be used
    public static HttpProtocolBuilder productProtocol = http
            .baseUrl("https://fakestoreapi.com")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    // Define the scenario
    private static ScenarioBuilder scn =
            scenario("Get Carts")
                    .exec(getAllCarts)
                    .pause(2)
                    .exec(getSpecificCart)
                    .pause(2)
                    .exec(createCart)
                    .pause(2)
                    .exec(updateCart)
                    .pause(1,5)
                    .exec(getSpecificCart)
                    .pause(1,10)
                    .exec(deleteCart)
                    .pause(2)
                    .exitHereIfFailed()
                    .exec(getAllCarts);
    {
        // Set up the scenario with user injection
        setUp(scn.injectOpen(rampUsers(50).during(30)))
                .protocols(productProtocol)
                .maxDuration(Duration.ofMinutes(1));
    }
}
