package internet_store.application.core.services;

import internet_store.application.database.Database;
import internet_store.application.core.domain.Product;

public class DeleteProductService {

private final Database database;

    public DeleteProductService(Database database) {
        this.database = database;
    }

    public boolean deleteByProductName(String productName) {
        return database.deleteByProductName(productName);
    }

    public boolean delete(Product product) {
        return database.delete(product);
    }

    public boolean delete(Long productIdLong) {
        return database.delete(productIdLong);
    }


}
