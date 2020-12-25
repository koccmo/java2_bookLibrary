package internet_store.lesson_6.core.services;

import internet_store.lesson_6.core.database.Database;
import internet_store.lesson_6.core.domain.Product;
import internet_store.lesson_6.core.requests.AddProductRequest;
import internet_store.lesson_6.core.responses.AddProductResponse;
import internet_store.lesson_6.core.responses.CoreError;
import internet_store.lesson_6.core.services.validators.AddProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddProductService {

    @Autowired
    private Database database;
    @Autowired
    private AddProductValidator validator;

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
