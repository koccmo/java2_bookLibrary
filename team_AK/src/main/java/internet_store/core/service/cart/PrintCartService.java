package internet_store.core.service.cart;

import internet_store.core.domain.Product;
import internet_store.database.cart_database.InnerCartDatabase;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class PrintCartService {
    @Autowired
    InnerCartDatabase cartDatabase;

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