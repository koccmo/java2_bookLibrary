package internet_store.core.service.cart;

import internet_store.core.domain.Cart;
import internet_store.database.interfaces.CartDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PrintCartService {
    @Autowired
    CartDatabase cartDatabase;

    public void print() {
        List<Cart> getAllProducts = cartDatabase.getCart();
        if (getAllProducts.isEmpty()) {
            System.out.println("No records");
            return;
        }
        System.out.println("Products in cart:");
        cartDatabase.showReport();
    }
}