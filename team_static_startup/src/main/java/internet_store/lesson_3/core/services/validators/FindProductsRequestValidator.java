package internet_store.lesson_3.core.services.validators;

import internet_store.lesson_3.core.requests.FindProductsRequest;
import internet_store.lesson_3.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class FindProductsRequestValidator {

    public List<CoreError> validate(FindProductsRequest request) {
        return new ArrayList<>(validateSearchFields(request));
    }

    private List<CoreError> validateSearchFields(FindProductsRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getName()) && isEmpty(request.getDescription())) {
            errors.add(new CoreError("Name", "Must not be empty!"));
            errors.add(new CoreError("Description", "Must not be empty!"));
        }
        return errors;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }


}
