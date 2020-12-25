package internet_store.core.service.product;

import internet_store.database.product_database.InnerProductDatabase;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class PrintProductService {
    @Autowired
    InnerProductDatabase productDatabase;

    public void print() {
        if (productDatabase.isEmpty()) {
            System.out.println("No records");
            return;
        }
        System.out.println("Products list:");
        productDatabase.showReport();
    }
}