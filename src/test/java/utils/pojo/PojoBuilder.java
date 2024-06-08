package utils.pojo;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PojoBuilder {

    private static Faker datafaker = new Faker();
    // Method to create a Product object
    public static Product createRandomProduct() {
        Product product = new Product();
        product.setTitle(datafaker.name().title());
        product.setPrice(datafaker.number().randomDouble(2, 1, 1000));
        product.setDescription(datafaker.name().name().repeat(10));
        product.setImage("https://i.pravatar.cc");
        product.setCategory(datafaker.commerce().department());
        return product;
    }

    public static Cart createRandomCart() {
        Faker faker = new Faker();
        Cart cart = new Cart();

        cart.setId(faker.number().numberBetween(1, 1000));
        cart.setDate(new Date());

        ProductList product = new ProductList();
        product.setId(faker.number().numberBetween(1, 1000));
        product.setQuantity(faker.number().numberBetween(1, 100));

        cart.setProducts(List.of(product));
        return cart;
    }


    public static void main(String[] args) {
        Cart randomCart = createRandomCart();
        System.out.println("CartId: " + randomCart.getId());
        System.out.println("Date: " + randomCart.getDate());
        System.out.println("Products:");
        for (ProductList product : randomCart.getProducts()) {
            System.out.println(product.getId());
            System.out.println(product.getQuantity());
        }
    }
}
