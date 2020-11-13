package internet_store.application.core.services;

import internet_store.application.database.Database;
import internet_store.application.core.domain.Product;

import java.math.BigDecimal;

public class AddProductService {

    private final Database database;

    public AddProductService(Database database) {
        this.database = database;
    }

    public void addProduct(String ProductName, String productDescription, BigDecimal price) {
        Product product = new Product(ProductName, productDescription, price);
        database.add(product);
    }

}
