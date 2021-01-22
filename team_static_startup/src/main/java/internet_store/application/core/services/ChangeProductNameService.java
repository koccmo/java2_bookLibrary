package internet_store.application.core.services;

import internet_store.application.core.database.ProductRepository;
import internet_store.application.core.requests.ChangeProductNameRequest;
import internet_store.application.core.responses.ChangeProductNameResponse;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.services.validators.ChangeProductNameValidator;
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
