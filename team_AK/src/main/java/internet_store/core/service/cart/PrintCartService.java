package internet_store.core.service.cart;

import internet_store.core.domain.Product;
import internet_store.database.cart_database.InnerCartDatabase;

import java.util.List;

public class PrintCartService {
    private final InnerCartDatabase cartDatabase;

    public PrintCartService(InnerCartDatabase cartDatabase) {
        this.cartDatabase = cartDatabase;
    }

    public void print() {
        List<Product> getAllProducts = cartDatabase.getCart();
        if (getAllProducts.isEmpty()) {
            System.out.println("No records");
            return;
        }
        System.out.println("Products in cart:");
        cartDatabase.showReport();
    }
}