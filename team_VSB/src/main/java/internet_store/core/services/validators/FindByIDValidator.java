package internet_store.core.services.validators;

import internet_store.core.requests.FindProductByIDRequest;
import internet_store.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class FindByIDValidator {

    public List<CoreError> validate(FindProductByIDRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (request.getProductId() == null || (request.getProductId().isBlank())) {
            errors.add(new CoreError("Product ID", "Can't be empty!"));
        } else try {
            Long.parseLong(request.getProductId());
        } catch (NumberFormatException exception) {
            errors.add(new CoreError("Product ID", "Should be valid!"));
        }
        return errors;
    }
}
