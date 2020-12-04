package internet_store.lesson_5.core.services;

import internet_store.lesson_5.core.database.Database;
import internet_store.lesson_5.core.domain.Product;
import internet_store.lesson_5.core.requests.FindProductsRequest;
import internet_store.lesson_5.core.responses.*;
import internet_store.lesson_5.core.services.validators.FindProductsRequestValidator;

import java.util.List;

public class FindProductsService {

    private final Database database;
    private final FindProductsRequestValidator validator;

    public FindProductsService(Database database,
                               FindProductsRequestValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public FindProductsResponse execute(FindProductsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindProductsResponse(null, errors);
        }

        List<Product> products = search(request);

        if (request.getOrdering() != null) {
            products = new OrderingProductsService().order(products, request.getOrdering());}
        if (request.getPaging() != null) {
            products = new PagingProductsService().page(products, request.getPaging());
        }
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
