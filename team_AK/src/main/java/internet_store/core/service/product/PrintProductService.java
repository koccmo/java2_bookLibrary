package internet_store.core.service.product;

import internet_store.database.product_database.InnerProductDatabase;

public class PrintProductService {
    final InnerProductDatabase productDatabase;

    public PrintProductService(InnerProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public void print() {
        if (productDatabase.isEmpty()) {
            System.out.println("No records");
            return;
        }
        System.out.println("Products list:");
        productDatabase.showReport();
    }
}