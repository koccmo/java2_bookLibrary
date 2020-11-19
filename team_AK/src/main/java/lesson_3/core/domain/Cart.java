package lesson_3.core.domain;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private final List<Product> clientProducts = new ArrayList<>();

    public void addProductToCart(Product product) {
        clientProducts.add(product);
    }

    public void deleteProductFromCart(Product product) {
        clientProducts.remove(product);
    }

    public List<Product> getCart() {
        return clientProducts;
    }
}