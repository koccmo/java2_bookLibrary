package java2.application_target_list.core.validators.target;

import org.springframework.stereotype.Component;
import java2.application_target_list.core.requests.target.ChangeTargetDeadlineRequest;
import java2.application_target_list.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeTargetDeadlineValidator {

    private List<CoreError> errors;

    public List<CoreError> validate(ChangeTargetDeadlineRequest changeTargetDeadlineRequest) {
        errors = new ArrayList<>();
        checkTargetId(changeTargetDeadlineRequest);
        checkTargetDeadline(changeTargetDeadlineRequest);
        return errors;
    }

    private void checkTargetDeadline(ChangeTargetDeadlineRequest changeTargetDeadlineRequest){
        if (isDeadlineNegative(changeTargetDeadlineRequest)){
            errors.add(createTargetDeadlineIsNegativeError());
        }

        if (isDeadlineEmpty(changeTargetDeadlineRequest)){
            errors.add(createTargetDeadlineIsEmptyError());
        }
    }

    private void checkTargetId(ChangeTargetDeadlineRequest changeTargetDeadlineRequest){
        if (isTargetIdEmpty(changeTargetDeadlineRequest)){
            errors.add(createTargetIdIsEmptyError());
        }
        if (isTargetIdNegative(changeTargetDeadlineRequest)){
            errors.add(createTargetIdIsNegativeError());
        }
    }

    private CoreError createTargetIdIsNegativeError(){
        return new CoreError("Target ID","must not be negative!");
    }

    private CoreError createTargetIdIsEmptyError(){
        return new CoreError("Target ID","must not be empty!");
    }

    private CoreError createTargetDeadlineIsEmptyError(){
        return new CoreError("Target new deadline", "must not be empty!");
    }

    private CoreError createTargetDeadlineIsNegativeError(){
        return new CoreError("Target new deadline", "must not be negative!");
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
