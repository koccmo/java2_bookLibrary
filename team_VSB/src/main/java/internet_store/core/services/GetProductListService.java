package internet_store.core.services;

import internet_store.core.database.Database;
import internet_store.core.domain.Product;
import internet_store.core.responses.PrintProductToConsoleResponse;

import java.util.List;

public class GetProductListService {

    private final Database database;

    public GetProductListService(Database database) { this.database = database; }

    public PrintProductToConsoleResponse getProductList() {
        List<Product> productList = database.getProductList();
        return new PrintProductToConsoleResponse(productList);
    }
}
