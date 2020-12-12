package internet_store.application.core.services;

import internet_store.application.core.database.Database;
import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.AddProductRequest;
import internet_store.application.core.responses.AddProductResponse;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.services.validators.AddProductValidator;
import internet_store.application.dependency_injection.DIDependency;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddProductService {

    @DIDependency private Database database;
    @DIDependency private AddProductValidator validator;

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
