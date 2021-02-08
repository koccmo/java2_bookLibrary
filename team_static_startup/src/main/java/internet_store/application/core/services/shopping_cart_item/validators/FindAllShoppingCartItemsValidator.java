package internet_store.application.core.services.shopping_cart_item.validators;

import internet_store.application.core.requests.shopping_cart_item.FindAllShoppingCartItemsRequest;
import internet_store.application.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindAllShoppingCartItemsValidator {

    public List<CoreError> validate(FindAllShoppingCartItemsRequest request) {
        List<CoreError> errors = new ArrayList<>();

        return errors;
    }

}
