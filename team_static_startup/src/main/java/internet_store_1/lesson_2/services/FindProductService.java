package internet_store_1.lesson_2.services;

import internet_store_1.lesson_2.database.Database;
import internet_store_1.lesson_2.domain.Product;

import java.util.List;
import java.util.Optional;

public class FindProductService {

    Database database;

    public FindProductService(Database database) {
        this.database = database;
    }

    public List<Product> findByProductName(String productName) {
        return database.findByProductName(productName);
    }

    public Optional<Product> findById(Long productId) {
        return database.findById(productId);
    }


}
