package internet_store.core.service.product;

import internet_store.database.product_database.ProductDatabaseImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrintProductService {
    @Autowired
    ProductDatabaseImpl productDatabase;

    public void print() {
        if (productDatabase.isEmpty()) {
            System.out.println("No records");
            return;
        }
        System.out.println("Products list:");
        productDatabase.showReport();
    }
}