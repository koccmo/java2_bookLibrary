package internet_store.application.core.services.order.validators;

import internet_store.application.core.requests.order.AddOrderRequest;
import internet_store.application.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddOrderValidator {

    public List<CoreError> validate(AddOrderRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (request.getShoppingCartId() == null || request.getShoppingCartId() <= 0) {
            errors.add(new CoreError("Shopping Cart ID", "should be greater than zero"));
        }
        return errors;
    }

}
