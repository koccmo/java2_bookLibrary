package estore.core.service;

import estore.database.ProductDB;
import estore.domain.Product;
import estore.core.requests.ShowAllProductsRequest;
import estore.core.responses.ShowAllProductsResponse;

import java.util.List;

public class ShowAllProductsService {

    private ProductDB productDatabase;

    public ShowAllProductsService(ProductDB productDatabase) {
        this.productDatabase = productDatabase;
    }

    public ShowAllProductsResponse execute(ShowAllProductsRequest request) {
        List<Product> foundProducts = productDatabase.getDatabase();
        return new ShowAllProductsResponse(foundProducts);
    }

}
