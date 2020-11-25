package internet_store_1.lesson_2.services;

import internet_store_1.lesson_2.database.Database;
import internet_store_1.lesson_2.domain.Product;

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
