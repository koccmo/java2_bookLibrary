package internet_store.application.core.services.product;

import internet_store.application.core.database.product.ProductRepository;
import internet_store.application.core.domain.Product;
import internet_store.application.core.requests.product.AddProductRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.product.AddProductResponse;
import internet_store.application.core.services.product.validators.AddProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AddProductValidator validator;

    public AddProductResponse execute(AddProductRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddProductResponse(errors);
        }

        Product product = new Product(request.getProductName(), request.getProductDescription(), request.getProductPrice());
        productRepository.add(product);
        return new AddProductResponse(product);
    }
}
