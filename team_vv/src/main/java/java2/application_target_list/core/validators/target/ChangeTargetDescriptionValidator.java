package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.requests.target.ChangeTargetDescriptionRequest;
import org.springframework.stereotype.Component;
import java2.application_target_list.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeTargetDescriptionValidator {

    private List<CoreError> errors;

    public List<CoreError> validate(ChangeTargetDescriptionRequest changeTargetDescriptionRequest) {
        errors = new ArrayList<>();
        checkTargetId(changeTargetDescriptionRequest);
        checkTargetDescription(changeTargetDescriptionRequest);
        return errors;
    }

    private void checkTargetDescription(ChangeTargetDescriptionRequest changeTargetDescriptionRequest){
        if (isTargetDescriptionEmpty(changeTargetDescriptionRequest)) {
            errors.add(createTargetDescriptionIsEmptyError());
        }
    }

    private void checkTargetId(ChangeTargetDescriptionRequest changeTargetDescriptionRequest){
        if (isTargetIdEmpty(changeTargetDescriptionRequest)) {
            errors.add(createTargetIdIsEmptyError());
        }
        if (isTargetIdNegative(changeTargetDescriptionRequest)) {
            errors.add(createTargetIdIsNegative());
        }
    }

    private CoreError createTargetIdIsNegative(){
        return new CoreError("Target ID", "must not be negative!");
    }

    private CoreError createTargetIdIsEmptyError(){
        return new CoreError("Target ID", "must not be empty!");
    }

    private CoreError createTargetDescriptionIsEmptyError(){
        return new CoreError("Target new description", "must not be empty!");
    }

    private boolean isTargetDescriptionEmpty(ChangeTargetDescriptionRequest request) {
        return request.getNewTargetDescription() == null || request.getNewTargetDescription().isEmpty();
    }

    private boolean isTargetIdEmpty(ChangeTargetDescriptionRequest request) {
        return request.getTargetIdToChange() == null;
    }

    private boolean isTargetIdNegative(ChangeTargetDescriptionRequest request) {
        return request.getTargetIdToChange() < 0;
    }

}
