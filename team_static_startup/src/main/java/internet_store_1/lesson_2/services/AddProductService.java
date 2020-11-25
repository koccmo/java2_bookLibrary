package internet_store_1.lesson_2.services;

import internet_store_1.lesson_2.database.Database;
import internet_store_1.lesson_2.domain.Product;

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
