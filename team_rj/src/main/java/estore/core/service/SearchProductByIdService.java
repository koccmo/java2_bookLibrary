package estore.core.service;

import estore.core.requests.SearchProductByIdRequest;
import estore.core.responses.SearchProductByIdResponse;
import estore.core.validation.CoreError;
import estore.core.validation.SearchProductByIdValidator;
import estore.database.ProductRepository;
import estore.core.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchProductByIdService {

    private ProductRepository productDB;
    private SearchProductByIdValidator validator;

    public SearchProductByIdService(ProductRepository productDB, SearchProductByIdValidator validator) {
        this.productDB = productDB;
        this.validator = validator;
    }

    public SearchProductByIdResponse execute(SearchProductByIdRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new SearchProductByIdResponse(errors);
        }

        List<Product> productList = productDB.searchProductById(Long.valueOf(request.getProductId()));
        Product foundProduct;
        int productsFound;
        if (productList.size() == 0) {
            foundProduct = null;
            productsFound = 0;
        } else {
            foundProduct = productList.get(0);
            productsFound = 1;
        }
        return new SearchProductByIdResponse(foundProduct, productsFound);
    }
}
