package estore.service;

import estore.database.ProductDataBase;
import estore.domain.Product;
import estore.requests.SearchProductByNameRequest;
import estore.responses.SearchProductByNameResponse;

import java.util.List;

public class SearchProductByNameService {

    private ProductDataBase database;

    public SearchProductByNameService(ProductDataBase database) {
        this.database = database;
    }

    public SearchProductByNameResponse execute(SearchProductByNameRequest request) {
        List<Product> foundProducts = database.searchProductByName(request.getProductName());
        return new SearchProductByNameResponse(foundProducts);
    }

}
