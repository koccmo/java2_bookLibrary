package internet_store.application.core.services;

import internet_store.application.database.Database;
import internet_store.application.core.domain.Product;

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
