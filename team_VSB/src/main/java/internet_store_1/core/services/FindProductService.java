package internet_store_1.core.services;

import internet_store_1.core.database.Database;
import internet_store_1.core.domain.Product;
import internet_store_1.core.requests.FindProductByNameRequest;

import java.util.List;
import java.util.Optional;

public class FindProductService {

    private final Database database;

    public FindProductService(Database database) { this.database = database; }

    public List<Product> findByNameProduct(FindProductByNameRequest nameRequest) {
        return database.findByProductName(nameRequest.getProductName());
    }

    public Optional<Product> findById (Long productId) {
        return database.findById(productId);
    }
}
