package internet_store.lesson_6.core.services.validators;

import internet_store.lesson_6.core.requests.DeleteByProductIdRequest;
import internet_store.lesson_6.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteByProductIdValidator {

    public List<CoreError> validate (DeleteByProductIdRequest request){
        List<CoreError> errors = new ArrayList<>();
        if (request.getProductId() == null) {
            errors.add(new CoreError("Product ID", "Should not be empty."));
        } return errors;
    }
}
