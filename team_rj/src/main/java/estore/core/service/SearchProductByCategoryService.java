package estore.core.service;

import estore.core.domain.ProductCategory;
import estore.core.requests.Ordering;
import estore.core.requests.Paging;
import estore.core.requests.SearchProductByCategoryRequest;
import estore.core.responses.SearchProductByCategoryResponse;
import estore.core.validation.CoreError;
import estore.core.validation.SearchProductByCategoryValidator;
import estore.database.ProductCategoryRepository;
import estore.database.ProductRepository;
import estore.core.domain.Product;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchProductByCategoryService {

    private ProductRepository productDB;
    private ProductCategoryRepository categoryDB;
    private SearchProductByCategoryValidator validator;

    public SearchProductByCategoryService(ProductRepository productDB,
                                          ProductCategoryRepository categoryDB,
                                          SearchProductByCategoryValidator validator) {
        this.productDB = productDB;
        this.categoryDB = categoryDB;
        this.validator = validator;
    }

    public SearchProductByCategoryResponse execute(SearchProductByCategoryRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new SearchProductByCategoryResponse(errors);
        }

        Long categoryId = null;
        List<ProductCategory> categories = categoryDB.getDatabase();
        for (var item : categories) {
            if (item.getCategory().equalsIgnoreCase(request.getProductCategory())) {
                categoryId = item.getId();
            }
        }

        List<Product> foundProducts = productDB.searchProductByCategory(categoryId);
        foundProducts = order(foundProducts, request.getOrdering());
        foundProducts = paging(foundProducts, request.getPaging());
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
            return products.stream()
                    .sorted(comparator)
                    .collect(Collectors.toList());
        } else {
            return products;
        }
    }

    private List<Product> paging(List<Product> products, Paging paging) {
        if (paging != null) {
            int pageSize = Integer.valueOf(paging.getPageSize());
            int skip = (Integer.valueOf(paging.getPageNumber()) - 1) * pageSize;
            return products.stream()
                    .skip(skip)
                    .limit(pageSize)
                    .collect(Collectors.toList());
        } else {
            return products;
        }
    }

}
