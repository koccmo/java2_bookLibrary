package estore.core.service;

import estore.core.requests.SearchProductByCategoryRequest;
import estore.core.responses.SearchProductByCategoryResponse;
import estore.core.validation.CoreError;
import estore.core.validation.SearchProductByCategoryValidator;
import estore.database.ProductDB;
import estore.domain.Product;

import java.util.List;

public class SearchProductByCategoryService {

    private ProductDB productDatabase;
    private SearchProductByCategoryValidator validator;

    public SearchProductByCategoryService(ProductDB productDatabase, SearchProductByCategoryValidator validator) {
        this.productDatabase = productDatabase;
        this.validator = validator;
    }

    public SearchProductByCategoryResponse execute(SearchProductByCategoryRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new SearchProductByCategoryResponse(errors);
        }

        List<Product> foundProducts = productDatabase.searchProductByCategory(request.getProductCategory());
        return new SearchProductByCategoryResponse(foundProducts, foundProducts.size());
    }

}
