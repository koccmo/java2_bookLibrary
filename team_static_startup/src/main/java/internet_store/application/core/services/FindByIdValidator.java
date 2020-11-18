package internet_store.application.core.services;

import internet_store.application.core.requests.FindByIdRequest;
import internet_store.application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class FindByIdValidator {

    public List<CoreError> validate(FindByIdRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (request.getProductId() == null || request.getProductId().toString().isEmpty()) {
            errors.add(new CoreError("Product ID", "Should not be empty."));
        }
        return errors;
    }


}
