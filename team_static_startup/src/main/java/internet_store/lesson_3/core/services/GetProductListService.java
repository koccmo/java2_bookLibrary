package internet_store.lesson_3.core.services;

import internet_store.lesson_3.core.domain.Product;
import internet_store.lesson_3.core.responses.PrintProductsToConsoleResponse;
import internet_store.lesson_3.database.Database;

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
