package internet_store.lesson_3.core.services;

import internet_store.lesson_3.core.domain.Product;
import internet_store.lesson_3.core.requests.FindProductsRequest;
import internet_store.lesson_3.core.responses.CoreError;
import internet_store.lesson_3.core.responses.FindProductsResponse;
import internet_store.lesson_3.core.services.validators.FindProductsRequestValidator;
import internet_store.lesson_3.database.Database;

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

        return new FindProductsResponse(products, null);
    }

}
