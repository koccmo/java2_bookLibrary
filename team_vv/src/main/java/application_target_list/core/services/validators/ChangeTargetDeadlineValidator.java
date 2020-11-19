package application_target_list.core.services.validators;

import application_target_list.core.requests.ChangeTargetDeadlineRequest;
import application_target_list.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class ChangeTargetDeadlineValidator {

    public List<CoreError> validate(ChangeTargetDeadlineRequest request) {

        List<CoreError> errors = new ArrayList<>();

        if (isTargetIdEmpty(request)){
            errors.add(new CoreError("Target ID","Must not be empty!"));
        }
        if (isTargetIdNegative(request)){
            errors.add(new CoreError("Target ID","Must not be negative!"));
        }
        if (isDeadlineNegative(request)){
            errors.add(new CoreError("Target new deadline", "Must not be negative!"));
        }

        return errors;
    }


    private boolean isTargetIdEmpty(ChangeTargetDeadlineRequest request) {
        return request.getTargetIdToChange() == null;
    }

    private boolean isTargetIdNegative(ChangeTargetDeadlineRequest request){
        return request.getTargetIdToChange() < 0;
    }
    private boolean isDeadlineNegative(ChangeTargetDeadlineRequest request){
        return request.getNewTargetDeadline() < 0;
    }

}
