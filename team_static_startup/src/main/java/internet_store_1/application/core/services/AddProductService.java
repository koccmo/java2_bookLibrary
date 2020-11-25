package internet_store_1.application.core.services;

import internet_store_1.application.core.requests.AddProductRequest;
import internet_store_1.application.core.responses.AddProductResponse;
import internet_store_1.application.core.responses.CoreError;
import internet_store_1.application.database.Database;
import internet_store_1.application.core.domain.Product;

import java.util.List;

public class AddProductService {

    private final Database database;
    private final AddProductValidator validator;

    public AddProductService(Database database, AddProductValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public AddProductResponse execute(AddProductRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddProductResponse(errors);
        }

        Product product = new Product(request.getProductName(), request.getProductDescription(), request.getProductPrice());
        database.add(product);
        return new AddProductResponse(product);
    }
}
