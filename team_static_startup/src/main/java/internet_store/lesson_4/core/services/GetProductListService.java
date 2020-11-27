package internet_store.lesson_4.core.services;

import internet_store.lesson_4.core.database.Database;
import internet_store.lesson_4.core.domain.Product;
import internet_store.lesson_4.core.responses.PrintProductsToConsoleResponse;

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
