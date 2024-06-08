package fakestoreapi.products;


import fakestoreapi.feeders.FeederImplementation;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static utils.chainers.ChainProductRequests.*;


public class ProductStressTests extends Simulation implements FeederImplementation {

    // Define the HTTP protocol to be used
    public static HttpProtocolBuilder productProtocol = http
            .baseUrl("https://fakestoreapi.com")
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    // Define the scenario
    private static ScenarioBuilder scn =
            scenario("Get products")
                    .exec(getAllProducts)
                    .pause(2)
                    .exec(getSpecificProduct)
                    .pause(2)
                    .exec(createProduct)
                    .pause(2)
                    .exec(updateProduct)
                    .pause(1,5)
                    .exec(getSpecificProduct)
                    .pause(1,10)
                    .exec(deleteProduct)
                    .pause(2)
                    .exitHereIfFailed()
                    .exec(getAllProducts);
    {
        // Set up the scenario with user injection
        setUp(scn.injectOpen(rampUsers(50).during(30)))
                .protocols(productProtocol)
                .maxDuration(Duration.ofMinutes(1));
    }
}
