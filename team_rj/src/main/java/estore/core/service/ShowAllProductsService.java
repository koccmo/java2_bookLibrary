package estore.core.service;

import estore.database.ProductDB;
import estore.domain.Product;
import estore.core.requests.ShowAllProductsRequest;
import estore.core.responses.ShowAllProductsResponse;

import java.util.List;

public class ShowAllProductsService {

    private ProductDB productDB;

    public ShowAllProductsService(ProductDB productDB) {
        this.productDB = productDB;
    }

    public ShowAllProductsResponse execute(ShowAllProductsRequest request) {
        List<Product> foundProducts = productDB.getDatabase();
        return new ShowAllProductsResponse(foundProducts);
    }

}
