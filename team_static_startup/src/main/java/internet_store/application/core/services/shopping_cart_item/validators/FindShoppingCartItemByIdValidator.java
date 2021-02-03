package internet_store.application.core.services.shopping_cart_item.validators;

import internet_store.application.core.requests.shopping_cart_item.FindShoppingCartItemByIdRequest;
import internet_store.application.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindShoppingCartItemByIdValidator {

    public List<CoreError> validate(FindShoppingCartItemByIdRequest request) {
        List<CoreError> errors = new ArrayList<>();

        return errors;
    }

}
