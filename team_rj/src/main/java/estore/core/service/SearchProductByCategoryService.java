package estore.core.service;

import estore.core.requests.Ordering;
import estore.core.requests.SearchProductByCategoryRequest;
import estore.core.responses.SearchProductByCategoryResponse;
import estore.core.validation.CoreError;
import estore.core.validation.SearchProductByCategoryValidator;
import estore.database.ProductDB;
import estore.domain.Product;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchProductByCategoryService {

    private ProductDB productDB;
    private SearchProductByCategoryValidator validator;

    public SearchProductByCategoryService(ProductDB productDB, SearchProductByCategoryValidator validator) {
        this.productDB = productDB;
        this.validator = validator;
    }

    public SearchProductByCategoryResponse execute(SearchProductByCategoryRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new SearchProductByCategoryResponse(errors);
        }

        List<Product> foundProducts = productDB.searchProductByCategory(request.getProductCategory());
        foundProducts = order(foundProducts, request.getOrdering());
        return new SearchProductByCategoryResponse(foundProducts, foundProducts.size());
    }

    private List<Product> order(List<Product> products, Ordering ordering) {
        if (ordering != null) {
            Comparator<Product> comparator = ordering.getOrderBy().toLowerCase().equals("name")
                    ? Comparator.comparing(Product::getName)
                    : Comparator.comparing(Product::getPrice);
            if (ordering.getOrderDirection().toLowerCase().equals("descending") ||
                    ordering.getOrderDirection().toLowerCase().equals("desc")) {
                comparator = comparator.reversed();
            }
            return products.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return products;
        }
    }

}
