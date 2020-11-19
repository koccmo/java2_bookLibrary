package application_target_list.core.services.validators;

import application_target_list.core.requests.ChangeTargetNameRequest;
import application_target_list.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class ChangeTargetNameValidator {

    public List<CoreError> validate(ChangeTargetNameRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (isTargetNameEmpty(request)){
            errors.add(new CoreError("Target new name","Must not be empty!"));
        }

        if (isTargetIdEmpty(request)){
            errors.add(new CoreError("Target ID","Must not be empty!"));
        }
        if (isTargetIdNegative(request)){
            errors.add(new CoreError("Target ID","Must not be negative!"));
        }

        return errors;
    }

    private boolean isTargetNameEmpty(ChangeTargetNameRequest request) {
        return request.getNewTargetName() == null || request.getNewTargetName().isEmpty();
    }

    private boolean isTargetIdEmpty(ChangeTargetNameRequest request) {
        return request.getTargetIdToChange() == null;
    }

    private boolean isTargetIdNegative(ChangeTargetNameRequest request){
        return request.getTargetIdToChange() < 0;
    }


}
