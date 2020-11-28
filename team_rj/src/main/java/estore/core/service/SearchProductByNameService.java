package estore.core.service;

import estore.core.validation.CoreError;
import estore.core.validation.SearchProductByNameValidator;
import estore.database.ProductDB;
import estore.domain.Product;
import estore.core.requests.SearchProductByNameRequest;
import estore.core.responses.SearchProductByNameResponse;

import java.util.List;

public class SearchProductByNameService {

    private ProductDB productDB;
    private SearchProductByNameValidator validator;

    public SearchProductByNameService(ProductDB productDB, SearchProductByNameValidator validator) {
        this.productDB = productDB;
        this.validator = validator;
    }

    public SearchProductByNameResponse execute(SearchProductByNameRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new SearchProductByNameResponse(errors);
        }

        List<Product> foundProducts = productDB.searchProductByName(request.getProductName());
        return new SearchProductByNameResponse(foundProducts, foundProducts.size());
    }

}
