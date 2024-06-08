package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import utils.pojo.PojoBuilder;

public class PojoConverter {

    // Convert Product object to JSON string
    public static String productToJson(Object pojo) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(pojo);
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert product to JSON", e);
        }
    }

    // Function to create a random product and return its JSON representation
    public static String createRandomProductJson(Object pojo) {
        pojo = PojoBuilder.createRandomProduct();
        return productToJson(pojo);
    }
}
