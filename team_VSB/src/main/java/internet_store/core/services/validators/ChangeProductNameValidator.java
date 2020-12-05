package internet_store.core.services.validators;

import internet_store.core.requests.ChangeProductNameRequest;
import internet_store.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class ChangeProductNameValidator {

    public List<CoreError> validate(ChangeProductNameRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (request.getProductNameNew() == null || (request.getProductNameNew().isBlank())) {
            errors.add(new CoreError("Product new name", "Can't be empty!"));
        }
        return errors;
    }


}
