package internet_store.application.core.services.validators;

import internet_store.application.core.requests.FindProductsRequest;
import internet_store.application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class FindProductsRequestValidator {

    public List<CoreError> validate(FindProductsRequest request) {
        return new ArrayList<>(validateSearchFields(request));
    }

    private List<CoreError> validateSearchFields(FindProductsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getName()) && isEmpty(request.getDescription())) {
            errors.add(new CoreError("name", "Must not be empty!"));
            errors.add(new CoreError("description", "Must not be empty!"));
        }
        return errors;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }


}
