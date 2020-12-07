package internet_store.core.service.product;

import internet_store.database.product_database.InnerProductDatabase;
import dependency.annotation.DIComponent;
import dependency.annotation.DIDependency;

@DIComponent
public class PrintProductService {
    @DIDependency
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