package internet_store.lesson_3.core.services.validators;

import internet_store.lesson_3.core.requests.ChangeProductNameRequest;
import internet_store.lesson_3.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class ChangeProductNameValidator {


    public List<CoreError> validate(ChangeProductNameRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (request.getProductNewName() == null || (request.getProductNewName().isBlank())) {
            errors.add(new CoreError("Product new name", "Should not be empty."));
        } return errors;
    }

}
