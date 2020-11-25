package internet_store_1.application.core.services;

import internet_store_1.application.core.domain.Product;
import internet_store_1.application.core.responses.PrintProductsToConsoleResponse;
import internet_store_1.application.database.Database;

import java.util.List;

public class GetProductListService {

    private final Database database;

    public GetProductListService(Database database) {
        this.database = database;
    }

    public PrintProductsToConsoleResponse getProductList() {
        List<Product> productList = database.getProductList();
        return new PrintProductsToConsoleResponse(productList);
    }

}
