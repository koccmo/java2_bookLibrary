package estore.core.service;

import estore.core.requests.Ordering;
import estore.core.requests.Paging;
import estore.core.requests.SearchProductByIdRequest;
import estore.core.requests.SearchProductByNameRequest;
import estore.core.responses.SearchProductByIdResponse;
import estore.core.responses.SearchProductByNameResponse;
import estore.core.validation.CoreError;
import estore.core.validation.SearchProductByNameValidator;
import estore.database.ProductDB;
import estore.domain.Product;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchProductByIdService {

    private ProductDB productDB;
//    private SearchProductByIdValidator validator;

//    public SearchProductByIdService(ProductDB productDB, SearchProductByIdValidator validator) {
//        this.productDB = productDB;
//        this.validator = validator;
//    }


    public SearchProductByIdService(ProductDB productDB) {
        this.productDB = productDB;
    }

    public SearchProductByIdResponse execute(SearchProductByIdRequest request) {
//        List<CoreError> errors = validator.validate(request);

//        if (!errors.isEmpty()) {
//            return new SearchProductByNameResponse(errors);
//        }

        Product foundProduct = productDB.searchProductById(Long.valueOf(request.getProductId()));
        return new SearchProductByIdResponse(foundProduct, 1);
//        List<Product> foundProducts = productDB.searchProductByName(request.getProductName());
//        foundProducts = order(foundProducts, request.getOrdering());
//        foundProducts = paging(foundProducts, request.getPaging());
//        return new SearchProductByNameResponse(foundProducts, foundProducts.size());
    }
}
