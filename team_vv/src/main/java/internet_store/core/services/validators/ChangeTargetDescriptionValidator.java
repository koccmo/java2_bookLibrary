package internet_store.core.services.validators;

import internet_store.core.requests.ChangeTargetDescriptionRequest;
import internet_store.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class ChangeTargetDescriptionValidator {

    public List<CoreError> validate(ChangeTargetDescriptionRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (isTargetDescriptionEmpty(request)){
            errors.add(new CoreError("Target new description","Must not be empty!"));
        }

        if (isTargetIdEmpty(request)){
            errors.add(new CoreError("Target ID","Must not be empty!"));
        }
        if (isTargetIdNegative(request)){
            errors.add(new CoreError("Target ID","Must not be negative!"));
        }

        return errors;
    }

    private boolean isTargetDescriptionEmpty(ChangeTargetDescriptionRequest request) {
        return request.getNewTargetDescription() == null || request.getNewTargetDescription().isEmpty();
    }

    private boolean isTargetIdEmpty(ChangeTargetDescriptionRequest request) {
        return request.getTargetIdTOChange() == null;
    }

    private boolean isTargetIdNegative(ChangeTargetDescriptionRequest request){
        return request.getTargetIdTOChange() < 0;
    }

}
