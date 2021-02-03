package internet_store.application.core.services.shopping_cart.validators;

import internet_store.application.core.requests.shopping_cart.AddShoppingCartRequest;
import internet_store.application.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddShoppingCartValidator {

    public List<CoreError> validate(AddShoppingCartRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (request.getCustomerId() == null || request.getCustomerId() <= 0) {
            errors.add(new CoreError("Customer ID", "should be greater than zero"));
        }

        return errors;
    }

}
