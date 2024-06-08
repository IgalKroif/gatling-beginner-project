package utils.pojo;

import com.github.javafaker.Faker;

import java.util.Date;
import java.util.List;

public class Cart extends ProductList {
    private Faker dataFaker = new Faker();
    private int userId;
    private Date date;
    private List<ProductList> products;

    public Cart() {
    }

    public Cart(int userId, Date date, List<ProductList> product) {
        this.userId = userId;
        this.date = date;
        this.products = product;
    }

    public int getId() {
        return userId;
    }

    public void setId(int id) {
        this.userId = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<ProductList> getProducts() {
        return products;
    }

    public void setProducts(List<ProductList> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + userId +
                ", date=" + date +
                ", products=" + products +
                '}';
    }

    public static class Product {
        private int productId;
        private int quantity;

        public Product(int productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}