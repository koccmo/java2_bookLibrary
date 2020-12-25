package internet_store.lesson_5.core.services.validators;

import internet_store.lesson_5.core.requests.DeleteByProductIdRequest;
import internet_store.lesson_5.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class DeleteByProductIdValidator {

    public List<CoreError> validate (DeleteByProductIdRequest request){
        List<CoreError> errors = new ArrayList<>();
        if (request.getProductId() == null) {
            errors.add(new CoreError("Product ID", "Should not be empty."));
        } return errors;
    }
}
