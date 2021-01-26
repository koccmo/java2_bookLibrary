package internet_store.application.core.services.product;

import internet_store.application.core.database.product.ProductRepository;
import internet_store.application.core.requests.product.FindByIdRequest;
import internet_store.application.core.responses.product.CoreError;
import internet_store.application.core.responses.product.FindByProductIdResponse;
import internet_store.application.core.services.product.validators.FindByProductIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindByProductIdService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private FindByProductIdValidator validator;

    public FindByProductIdResponse execute(FindByIdRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindByProductIdResponse(errors);
        }

        Long id = Long.parseLong(request.getProductId());
        return new FindByProductIdResponse(productRepository.findById(id));
    }
}

