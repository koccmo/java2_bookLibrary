package internet_store.application.core.services.shopping_cart_item.validators;

import internet_store.application.core.requests.shopping_cart_item.UpdateShoppingCartItemRequest;
import internet_store.application.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateShoppingCartItemValidator {

    public List<CoreError> validate(UpdateShoppingCartItemRequest request){
        List<CoreError> errors = new ArrayList<>();
        validateQuantity(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateQuantity(UpdateShoppingCartItemRequest request) {
        return (request.getQuantity() == null || request.getQuantity() <= 0)
                ? Optional.of(new CoreError("newQuantity", "Must not be empty or less than 1!"))
                : Optional.empty();
    }

}
