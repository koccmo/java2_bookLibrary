package internet_store.application.core.services.shopping_cart_item.validators;

import internet_store.application.core.requests.shopping_cart_item.AddShoppingCartItemRequest;
import internet_store.application.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddShoppingCartItemValidator {

    public List<CoreError> validate(AddShoppingCartItemRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (request.getQuantity() <= 0 || request.getQuantity() == null){
            errors.add(new CoreError("Quantity.", "Should be grater than zero."));
        }else if (request.getShoppingCartId() <= 0 || request.getShoppingCartId() == null) {
            errors.add(new CoreError("Shopping Cart Id.", "Should be grater than zero or not equal to NULL."));
        }else if (request.getProductId() <= 0 || request.getProductId() == null) {
            errors.add(new CoreError("Product Id.", "Should be grater than zero or not equal to NULL."));
        }
        return errors;
    }

}
