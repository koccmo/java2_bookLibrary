package lv.estore.app.core.services;

import lv.estore.app.core.domain.Product;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.database.ProductRepository;
import lv.estore.app.core.request.Ordering;
import lv.estore.app.core.request.Paging;
import lv.estore.app.core.request.SearchRequest;
import lv.estore.app.core.responses.SearchResponse;
import lv.estore.app.core.validators.SearchValidator;
import lv.estore.app.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired
    SearchValidator validator;

    @Autowired
    ProductRepository database;

    @Autowired
    CommonUtils commonUtils;

    /**
     * Method to search products.
     * @param request SearchRequest
     * @return response SearchResponse
     */
    public SearchResponse execute(final SearchRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchResponse(null, errors);
        }
        List<Product> products = search(request);
        products = order(products, request.getOrdering());
        products = paging(products, request.getPaging());
        return new SearchResponse(products, null);
    }

    private List<Product> search(final SearchRequest request) {
        List<Product> products = new ArrayList<>();
        if (request.isNameProvided() && !request.isPriceProvided()) {
            products = database.findByName(request.getName());
        }
        if (!request.isNameProvided() && request.isPriceProvided()) {
            products = database.findByPrice(commonUtils.createBigDecimal(request.getPrice()));
        }
        if (request.isNameProvided() && request.isPriceProvided()) {
            products = database.findManyByNameAndPrice(request.getName(), commonUtils.createBigDecimal(request.getPrice()));
        }
        return products;
    }

    private List<Product> order(List<Product> products, Ordering ordering) {
        if (orderingEnabled && (ordering != null)) {
            Comparator<Product> comparator = ordering.getOrderBy().equals("name")
                    ? Comparator.comparing(Product::getName)
                    : Comparator.comparing(Product::getPrice);
            if (ordering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return products.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return products;
        }
    }

    private List<Product> paging(List<Product> products, Paging paging) {
        if (pagingEnabled && (paging != null)) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return products.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return products;
        }
    }
}
