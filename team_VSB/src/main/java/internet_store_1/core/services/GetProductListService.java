package internet_store_1.core.services;

import internet_store_1.core.database.Database;
import internet_store_1.core.domain.Product;
import internet_store_1.core.responses.PrintProductToConsoleResponse;

import java.util.List;

public class GetProductListService {

    private final Database database;

    public GetProductListService(Database database) { this.database = database; }

    public PrintProductToConsoleResponse getProductList() {
        List<Product> productList = database.getProductList();
        return new PrintProductToConsoleResponse(productList);
    }
}
