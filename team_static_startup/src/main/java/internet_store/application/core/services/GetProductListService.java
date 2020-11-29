package internet_store.application.core.services;

import internet_store.application.core.domain.Product;
import internet_store.application.core.responses.PrintProductsToConsoleResponse;
import internet_store.application.core.database.Database;

import java.util.List;

public class GetProductListService {

    private final Database database;

    public GetProductListService(Database database) {
        this.database = database;
    }

    public PrintProductsToConsoleResponse execute() {
        List<Product> productList = database.getProductList();
        return new PrintProductsToConsoleResponse(productList);
    }

}
