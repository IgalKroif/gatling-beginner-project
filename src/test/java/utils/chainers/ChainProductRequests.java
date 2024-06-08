package utils.chainers;

import fakestoreapi.feeders.FeederImplementation;
import io.gatling.javaapi.core.ChainBuilder;
import utils.pojo.Product;

import static utils.pojo.PojoBuilder.createRandomProduct;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static utils.PojoConverter.createRandomProductJson;
import static utils.PojoConverter.productToJson;

public class ChainProductRequests implements FeederImplementation {
    // Define a chain to get all products using the product ID from the feeder
    public static ChainBuilder getAllProducts =
            exec(http("Get all products")
                    .get("/products/")  // Corrected placeholder
                    .check(status().is(200))
                    .check(jsonPath("$[?(@.category == 'electronics')].id").findAll().saveAs("electronicsProductIds"))
                    .check(jsonPath("$[0].title").is("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops"))
            );

    public static ChainBuilder getSpecificProduct = feed(randomizedProductIds)
            .exec(http("Get product by id - #{id}")
                    .get("/products/#{id}")
                    .check(status().is(200))
            );
    public static ChainBuilder getCircularSpecificProduct = feed(circularProductIds)
            .exec(http("Get product by id - #{id}")
                    .get("/products/#{id}")
                    .check(status().is(200))
            );
    // Chain to create a new product
    public static ChainBuilder createProduct = exec(session -> {
        Product product = createRandomProduct();
        String productJson = productToJson(product);
        System.out.println(productJson);
        return session.set("productJson", productJson);
    }).exec(http("Create a product")
            .post("/products")
            .body(StringBody("#{productJson}")).asJson()
            .check(status().in(200, 201))
    );
    // Chain to update a product
    public static ChainBuilder updateProduct = feed(circularProductIds)
            .exec(session -> {
                String productJson = createRandomProductJson(createProduct);
                System.out.println(productJson);
                return session.set("productJson", productJson);
            })
            .exec(http("Update a product - id :  #{id}")
                    .put("/products/#{id}") // Assuming you have a productId in your CSV
                    .body(StringBody("#{productJson}")).asJson()
                    .check(status().in(200, 201))
            );
    // Chain to update a product
    public static ChainBuilder deleteProduct = feed(randomizedProductIds)
            .exec(session -> {
                String productJson = createRandomProductJson(createProduct);
                System.out.println(productJson);
                return session.set("productJson", productJson);
            })
            .exec(http("Update a product - id :  #{id}")
                    .delete("/products/#{id}") // Assuming you have a productId in your CSV
                    .body(StringBody("#{productJson}")).asJson()
                    .check(status().in(200, 201))
            );
}
