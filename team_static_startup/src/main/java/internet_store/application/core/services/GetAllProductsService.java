package internet_store.application.core.services;

import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.GetAllProductsRequest;
import internet_store.application.core.responses.GetAllProductsResponse;
import internet_store.application.core.database.Database;

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
