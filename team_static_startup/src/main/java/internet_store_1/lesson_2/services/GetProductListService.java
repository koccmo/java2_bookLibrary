package internet_store_1.lesson_2.services;

import internet_store_1.lesson_2.database.Database;
import internet_store_1.lesson_2.domain.Product;

import java.util.List;

public class GetProductListService {

    private final Database database;

    public GetProductListService(Database database) {
        this.database = database;
    }

    public List<Product> getProductList() {
        return database.getProductList();
    }
}
