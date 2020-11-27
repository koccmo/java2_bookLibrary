package estore.core.service;

import estore.core.requests.SearchProductByCategoryRequest;
import estore.core.responses.SearchProductByCategoryResponse;
import estore.core.validation.CoreError;
import estore.core.validation.SearchProductByCategoryValidator;
import estore.database.ProductDataBase;
import estore.domain.Product;

import java.util.List;

public class SearchProductByCategoryService {

    private ProductDataBase database;
    private SearchProductByCategoryValidator validator;

    public SearchProductByCategoryService(ProductDataBase database, SearchProductByCategoryValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public SearchProductByCategoryResponse execute(SearchProductByCategoryRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new SearchProductByCategoryResponse(errors);
        }

        List<Product> foundProducts = database.searchProductByCategory(request.getProductCategory());
        return new SearchProductByCategoryResponse(foundProducts, foundProducts.size());
    }

}
