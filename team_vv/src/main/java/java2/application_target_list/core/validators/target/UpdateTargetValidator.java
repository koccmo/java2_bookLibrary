package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.requests.target.UpdateTargetRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateTargetValidator {


    public List<CoreError> validate(UpdateTargetRequest updateTargetRequest) {
        List<CoreError> errors = new ArrayList<>();

        if (isTargetIdEmpty(updateTargetRequest)){
            errors.add(new CoreError("Target ID","must not be empty!"));
        }
        if (isTargetIdNegative(updateTargetRequest)){
            errors.add(new CoreError("Target ID","must not be negative!"));
        }

        if (isTargetNameEmpty(updateTargetRequest)){
            errors.add(new CoreError("Target new name","must not be empty!"));
        }

        if (isTargetDescriptionEmpty(updateTargetRequest)){
            errors.add(new CoreError("Target new description","must not be empty!"));
        }

        if (isDeadlineNegative(updateTargetRequest)){
            errors.add(new CoreError("Target new deadline", "must not be negative!"));
        }

        if (isDeadlineEmpty(updateTargetRequest)){
            errors.add(new CoreError("Target new deadline", "must not be empty!"));
        }
        return errors;
    }

    private boolean isTargetNameEmpty(UpdateTargetRequest request) {
        return request.getNewTargetName() == null || request.getNewTargetName().isEmpty();
    }

    private boolean isTargetIdEmpty(UpdateTargetRequest request) {
        return request.getTargetIdToChange() == null;
    }

    private boolean isTargetIdNegative(UpdateTargetRequest request){
        return request.getTargetIdToChange() < 0;
    }

    private boolean isTargetDescriptionEmpty(UpdateTargetRequest request) {
        return request.getNewTargetDescription() == null || request.getNewTargetDescription().isEmpty();
    }

    private boolean isDeadlineEmpty(UpdateTargetRequest request) {
        return request.getNewTargetDeadline() == null;
    }

    private boolean isDeadlineNegative(UpdateTargetRequest request){
        return request.getNewTargetDeadline() < 0;
    }

}
