package internet_store.application.services;

import internet_store.application.database.Database;
import internet_store.application.domain.Product;

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
