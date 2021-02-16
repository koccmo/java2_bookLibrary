package lv.estore.app.core.services.products;

import lv.estore.app.core.database.products.ProductRepository;
import lv.estore.app.core.domain.Product;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.products.Ordering;
import lv.estore.app.core.request.products.Paging;
import lv.estore.app.core.request.products.SearchProductByNamePriceRequest;
import lv.estore.app.core.responses.products.SearchProductResponse;
import lv.estore.app.core.validators.products.ProductSearchValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static lv.estore.app.utils.CommonUtils.getBigDecimal;

@Component
public class SearchProductsService {

    @Value("${search.ordering.enabled}")
    private boolean orderingEnabled;

    @Value("${search.paging.enabled}")
    private boolean pagingEnabled;

    @Autowired
    ProductSearchValidator validator;

    @Autowired
    ProductRepository database;

    public SearchProductResponse execute(final SearchProductByNamePriceRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchProductResponse(null, errors);
        }
        List<Product> products = search(request);
        products = order(products, request.getOrdering());
        products = paging(products, request.getPaging());
        return new SearchProductResponse(products, null);
    }

    private List<Product> search(final SearchProductByNamePriceRequest request) {
        List<Product> products = new ArrayList<>();
        if (request.isNameProvided() && !request.isPriceProvided()) {
            products = database.findProductsByName(request.getName());
        }
        if (!request.isNameProvided() && request.isPriceProvided()) {
            products = database.findProductsByPrice(getBigDecimal(request.getPrice()));
        }
        if (request.isNameProvided() && request.isPriceProvided()) {
            products = database.findProductsByNameAndPrice(request.getName(), getBigDecimal(request.getPrice()));
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
