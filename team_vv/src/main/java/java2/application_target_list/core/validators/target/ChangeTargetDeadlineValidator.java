package java2.application_target_list.core.validators.target;

import org.springframework.stereotype.Component;
import java2.application_target_list.core.requests.target.ChangeTargetDeadlineRequest;
import java2.application_target_list.core.responses.CoreError;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeTargetDeadlineValidator {


    public List<CoreError> validate(ChangeTargetDeadlineRequest request) {

        List<CoreError> errors = new ArrayList<>();

        if (isTargetIdEmpty(request)){
            errors.add(new CoreError("Target ID","must not be empty!"));
        }
        if (isTargetIdNegative(request)){
            errors.add(new CoreError("Target ID","must not be negative!"));
        }
        if (isDeadlineNegative(request)){
            errors.add(new CoreError("Target new deadline", "must not be negative!"));
        }

        if (isDeadlineEmpty(request)){
            errors.add(new CoreError("Target new deadline", "must not be empty!"));
        }

        return errors;
    }

    private boolean isTargetIdEmpty(ChangeTargetDeadlineRequest request) {
        return request.getTargetIdToChange() == null;
    }

    private boolean isDeadlineEmpty(ChangeTargetDeadlineRequest request) {
        return request.getNewTargetDeadline() == null;
    }

    private boolean isTargetIdNegative(ChangeTargetDeadlineRequest request){
        return request.getTargetIdToChange() < 0;
    }
    private boolean isDeadlineNegative(ChangeTargetDeadlineRequest request){
        return request.getNewTargetDeadline() < 0;
    }

}
