package internet_store.application.core.services.validators;

import internet_store.application.core.requests.DeleteByProductIdRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class DeleteByProductIdValidator {

    public List<CoreError> validate (DeleteByProductIdRequest request){
        List<CoreError> errors = new ArrayList<>();
        if (request.getProductId() == null) {
            errors.add(new CoreError("Product ID", "Should not be empty."));
        } return errors;
    }
}
