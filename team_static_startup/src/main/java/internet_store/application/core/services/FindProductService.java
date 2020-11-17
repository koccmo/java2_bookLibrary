package internet_store.application.core.services;

import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.*;
import internet_store.application.database.Database;

import java.util.List;
import java.util.Optional;

public class FindProductService {

    private final Database database;

    public FindProductService(Database database) {
        this.database = database;
    }

    public List<Product> findByProductName(FindByProductNameRequest productNameRequest) {
        return database.findByProductName(productNameRequest.getProductName());
    }

    public Optional<Product> findById(Long productId) {
        return database.findById(productId);
    }

}
