package fakestoreapi.feeders;

import io.gatling.javaapi.core.FeederBuilder;

import static io.gatling.javaapi.core.CoreDsl.csv;

public interface FeederImplementation {
    // Define a feeder for product IDs from a CSV file
    public static FeederBuilder.Batchable<String> randomizedProductIds = csv("data/productIds.csv").random();

    public static FeederBuilder.Batchable<String> circularProductIds = csv("data/productIds.csv").circular();

    public static FeederBuilder.Batchable<String> randomizedCartIds = csv("data/productIds.csv").random();
    public static FeederBuilder.Batchable<String> circularCartIds = csv("data/productIds.csv").circular();
}
