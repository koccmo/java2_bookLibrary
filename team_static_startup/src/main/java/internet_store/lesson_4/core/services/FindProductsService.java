package internet_store.lesson_4.core.services;

import internet_store.lesson_4.core.database.Database;
import internet_store.lesson_4.core.domain.Product;
import internet_store.lesson_4.core.requests.FindProductsRequest;
import internet_store.lesson_4.core.responses.CoreError;
import internet_store.lesson_4.core.responses.FindProductsResponse;
import internet_store.lesson_4.core.services.validators.FindProductsRequestValidator;

import java.util.List;

public class FindProductsService {

    private final Database database;
    private final FindProductsRequestValidator validator;
    private final OrderingProductsService orderingProductsService;
    private final PagingProductsService pagingProductsService;

    public FindProductsService(Database database,
                               FindProductsRequestValidator validator,
                               OrderingProductsService orderingProductsService,
                               PagingProductsService pagingProductsService) {
        this.database = database;
        this.validator = validator;
        this.orderingProductsService = orderingProductsService;
        this.pagingProductsService = pagingProductsService;
    }

    public FindProductsResponse execute(FindProductsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindProductsResponse(null, errors);
        }

        List<Product> products = search(request);
        products = orderingProductsService.order(products, request.getOrdering());
        products = pagingProductsService.page(products, request.getPaging());
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
        return products;
    }

}
