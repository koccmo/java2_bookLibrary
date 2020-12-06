package internet_store.lesson_5.core.services;

import internet_store.lesson_5.core.database.Database;
import internet_store.lesson_5.core.domain.Product;
import internet_store.lesson_5.core.requests.GetAllProductsRequest;
import internet_store.lesson_5.core.responses.GetAllProductsResponse;

import java.util.List;

public class GetAllProductsService {

    private final Database database;

    public GetAllProductsService(Database database) {
        this.database = database;
    }

    public GetAllProductsResponse execute(GetAllProductsRequest request) {
        List<Product> productList = database.getProductList();
        return new GetAllProductsResponse(productList);
    }

}
