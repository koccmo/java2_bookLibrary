package internet_store.application.core.services.shopping_cart.validators;

import internet_store.application.core.requests.shopping_cart.FindShoppingCartByIdRequest;
import internet_store.application.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindShoppingCartByIdValidator {

    public List<CoreError> validate(FindShoppingCartByIdRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (request.getId() == null || request.getId() <= 0) {
            errors.add(new CoreError("ID", "should be greater than zero"));
        }

        return errors;
    }

}
