package internet_store.core.service.cart;

import internet_store.core.domain.Product;
import internet_store.database.cart_database.InnerCartDatabase;
import dependency.annotation.DIComponent;
import dependency.annotation.DIDependency;

import java.util.List;

@DIComponent
public class PrintCartService {
    @DIDependency
    InnerCartDatabase cartDatabase;

//    public PrintCartService() {}

//    public PrintCartService(InnerCartDatabase cartDatabase) {
//        this.cartDatabase = cartDatabase;
//    }

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