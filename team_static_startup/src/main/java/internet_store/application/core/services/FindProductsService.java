package internet_store.application.core.services;

import internet_store.application.core.database.Database;
import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.FindProductsRequest;
import internet_store.application.core.requests.Paging;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.FindProductsResponse;
import internet_store.application.core.services.validators.FindProductsRequestValidator;

import java.util.List;
import java.util.stream.Collectors;

public class FindProductsService {

    private final Database database;
    private final FindProductsRequestValidator validator;
    private final OrderingProductsService orderingProductsService;

    public FindProductsService(Database database,
                               FindProductsRequestValidator validator,
                               OrderingProductsService orderingProductsService) {
        this.database = database;
        this.validator = validator;
        this.orderingProductsService = orderingProductsService;
    }

    public FindProductsResponse execute(FindProductsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindProductsResponse(null, errors);
        }

        List<Product> products = search(request);
        products = orderingProductsService.order(products, request.getOrdering());

        return new FindProductsResponse(products, null);
    }

    private List<Product> search(FindProductsRequest request) {
        List<Product> products = null;
        if (request.isNameProvided() && !request.isDescriptionProvided()) {
            products = database.findByProductName(request.getName());
        }
        if (!request.isNameProvided() && request.isDescriptionProvided()) {
            products = database.findByProductDescription(request.getDescription());
        }
        if (request.isNameProvided() && request.isDescriptionProvided()) {
            products = database.findByNameAndDescription(request.getName(), request.getDescription());
        }

        products = applyPaging(products, request.getPaging());

        return products;
    }

    private List<Product> applyPaging(List<Product> products, Paging paging) {
        if (paging != null) {
            int numberOfProductsToSkip = paging.getPageSize() * (paging.getPageNumber() - 1);
            return products.stream()
                    .skip(numberOfProductsToSkip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return products;
        }
    }

}
