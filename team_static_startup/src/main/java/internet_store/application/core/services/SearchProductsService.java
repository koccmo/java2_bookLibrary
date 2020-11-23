package internet_store.application.core.services;

import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.SearchProductsRequest;
import internet_store.application.core.responses.*;
import internet_store.application.core.services.validators.SearchProductsRequestValidator;
import internet_store.application.database.Database;

import java.util.List;

public class SearchProductsService {

    private final Database database;
    private final SearchProductsRequestValidator validator;

    public SearchProductsService(Database database,
                              SearchProductsRequestValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public SearchProductsResponse execute(SearchProductsRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new SearchProductsResponse(null, errors);
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

        return new SearchProductsResponse(products, null);
    }

}
