package estore.core.service;

import estore.database.ProductDataBase;
import estore.domain.Product;
import estore.core.requests.ShowAllProductsRequest;
import estore.core.responses.ShowAllProductsResponse;

import java.util.List;

public class ShowAllProductsService {

    private ProductDataBase database;

    public ShowAllProductsService(ProductDataBase database) {
        this.database = database;
    }

    public ShowAllProductsResponse execute(ShowAllProductsRequest request) {
        List<Product> foundProducts = database.getDatabase();
        return new ShowAllProductsResponse(foundProducts);
    }

}
