package internet_store.application.core.services.shopping_cart_item.validators;

import internet_store.application.core.requests.shopping_cart_item.DeleteShoppingCartItemRequest;
import internet_store.application.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteShoppingCartItemValidator {

    public List<CoreError> validate(DeleteShoppingCartItemRequest request){
        List<CoreError> errors = new ArrayList<>();
        validateId(request).ifPresent(e -> errors.add(e));
        return errors;
    }

    private Optional<CoreError> validateId(DeleteShoppingCartItemRequest request){
        return (request.getId() == null)
                ? Optional.of(new CoreError("id", "must not be empty!"))
                : Optional.empty();
    }

}
