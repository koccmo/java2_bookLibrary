package lesson_3.core.service.print_products_from_cart;

import lesson_3.core.domain.Cart;
import lesson_3.core.domain.Product;

import java.util.List;

public class PrintCartService {
    private final Cart cart;

    public PrintCartService(Cart cart) {
        this.cart = cart;
    }

    public void print() {
        List<Product> getAllProducts = cart.getCart();
        if (getAllProducts.isEmpty()) {
            System.out.println("No records");
            return;
        }
        getAllProducts.forEach(pr -> System.out.println("ID: " + pr.getId() + " " +
                "Title: " + pr.getTitle() + " " + "Description: " + pr.getDescription() + " " +
                "Quantity: " + pr.getQuantity() + " " + "Price: " + pr.getPrice()));
    }
}