package utils.chainers;

import fakestoreapi.feeders.FeederImplementation;
import io.gatling.javaapi.core.ChainBuilder;
import utils.pojo.Cart;
import utils.pojo.Product;

import static utils.pojo.PojoBuilder.createRandomCart;
import static utils.pojo.PojoBuilder.createRandomProduct;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static utils.PojoConverter.createRandomProductJson;
import static utils.PojoConverter.productToJson;

public class ChainCartRequests implements FeederImplementation {
    // Define a chain to get all products using the product ID from the feeder
    public static ChainBuilder getAllCarts =
            exec(http("Get all carts")
                    .get("/carts/")  // Corrected placeholder
                    .check(status().is(200))
                    //.check(jsonPath("$[?(@.category == 'electronics')].id").findAll().saveAs("electronicsProductIds"))
                    //.check(jsonPath("$[0].title").is("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"))
            );

    public static ChainBuilder getSpecificCart = feed(randomizedProductIds)
            .exec(http("Get carts by id - #{id}")
                    .get("/carts/#{id}")
                    .check(status().is(200))
            );
    public static ChainBuilder getCircularSpecificCart = feed(circularProductIds)
            .exec(http("Get carts by id - #{id}")
                    .get("/carts/#{id}")
                    .check(status().is(200))
            );
    // Chain to create a new product
    public static ChainBuilder createCart = exec(session -> {
        Cart cart = createRandomCart();
        String productJson = productToJson(cart);
        System.out.println(productJson);
        return session.set("productJson", productJson);
    }).exec(http("Create a cart")
            .post("/carts")
            .body(StringBody("#{productJson}")).asJson()
            .check(status().in(200, 201))
    );
    // Chain to update a product
    public static ChainBuilder updateCart = feed(circularProductIds)
            .exec(session -> {
                String productJson = createRandomProductJson(createRandomCart());
                System.out.println(productJson);
                return session.set("productJson", productJson);
            })
            .exec(http("Update a cart - id :  #{id}")
                    .put("/carts/#{id}") // Assuming you have a productId in your CSV
                    .body(StringBody("#{productJson}")).asJson()
                    .check(status().in(200, 201))
            );
    // Chain to update a product
    public static ChainBuilder deleteCart = feed(randomizedProductIds)
            .exec(session -> {
                String productJson = createRandomProductJson(createRandomCart());
                System.out.println(productJson);
                return session.set("productJson", productJson);
            })
            .exec(http("Delete a cart - id :  #{id}")
                    .delete("/carts/#{id}") // Assuming you have a productId in your CSV
                    .body(StringBody("#{productJson}")).asJson()
                    .check(status().in(200, 201))
            );
}
