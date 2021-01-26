package internet_store.application.core.services.product;

import internet_store.application.core.database.product.ProductRepository;
import internet_store.application.core.requests.product.ChangeProductNameRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.product.ChangeProductNameResponse;
import internet_store.application.core.services.product.validators.ChangeProductNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChangeProductNameService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ChangeProductNameValidator validator;

    public ChangeProductNameResponse execute(ChangeProductNameRequest request) {
        List<CoreError> errors = validator.validate(request);
        Long id = request.getProductId();
        String newName = request.getProductNewName();

        if (!errors.isEmpty()) {
            return new ChangeProductNameResponse(errors);
        } else return new ChangeProductNameResponse(productRepository.changeProductName(id, newName));
    }


}
