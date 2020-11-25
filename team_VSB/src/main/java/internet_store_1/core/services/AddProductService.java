package internet_store_1.core.services;

import internet_store_1.core.database.Database;
import internet_store_1.core.domain.Product;

import java.math.BigDecimal;

public class AddProductService {

    private final Database database;

    public AddProductService(Database database) {
        this.database = database;
    }

    public void addProduct(String productName, String productDescription, BigDecimal price) {
        Product product = new Product(productName, productDescription, price);
        database.add(product);
    }
}
