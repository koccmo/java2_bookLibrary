package lesson_3.core.services.validators;

import lesson_3.core.requests.ChangeTargetDescriptionRequest;
import lesson_3.core.responses.CoreError;

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
