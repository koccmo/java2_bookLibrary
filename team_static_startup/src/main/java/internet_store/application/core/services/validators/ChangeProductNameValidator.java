package internet_store.application.core.services.validators;

import internet_store.application.core.requests.ChangeProductNameRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class ChangeProductNameValidator {

    public List<CoreError> validate(ChangeProductNameRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (request.getProductNewName() == null || (request.getProductNewName().isBlank())) {
            errors.add(new CoreError("Product new name", "Should not be empty."));
        } return errors;
    }

}
