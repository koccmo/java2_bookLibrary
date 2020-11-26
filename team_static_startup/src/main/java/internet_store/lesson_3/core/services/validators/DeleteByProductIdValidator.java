package internet_store.lesson_3.core.services.validators;

import internet_store.lesson_3.core.requests.DeleteByProductIdRequest;
import internet_store.lesson_3.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class DeleteByProductIdValidator {

    public List<CoreError> validate (DeleteByProductIdRequest request){
        List<CoreError> errors = new ArrayList<>();

        if (request.getProductId() == null || (request.getProductId().isBlank())) {
            errors.add(new CoreError("Product ID", "Should not be empty."));
        } else try {
            Long.parseLong(request.getProductId());
        } catch (NumberFormatException e) {
            errors.add(new CoreError("Product ID", "Should be number."));
        }
        return errors;
    }

}
