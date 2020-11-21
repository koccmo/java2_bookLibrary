package estore.core.service;

import estore.core.validation.CoreError;
import estore.core.validation.SearchProductByNameValidator;
import estore.database.ProductDataBase;
import estore.domain.Product;
import estore.core.requests.SearchProductByNameRequest;
import estore.core.responses.SearchProductByNameResponse;

import java.util.List;

public class SearchProductByNameService {

    private ProductDataBase database;
    private SearchProductByNameValidator validator;

    public SearchProductByNameService(ProductDataBase database, SearchProductByNameValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public SearchProductByNameResponse execute(SearchProductByNameRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new SearchProductByNameResponse(errors);
        }

        List<Product> foundProducts = database.searchProductByName(request.getProductName());
        return new SearchProductByNameResponse(foundProducts, foundProducts.size());
    }

}
