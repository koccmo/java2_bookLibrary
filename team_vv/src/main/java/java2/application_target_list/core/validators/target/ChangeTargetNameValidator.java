package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.requests.target.ChangeTargetNameRequest;
import org.springframework.stereotype.Component;
import java2.application_target_list.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeTargetNameValidator {

    private List<CoreError> errors;

    public List<CoreError> validate(ChangeTargetNameRequest changeTargetNameRequest) {
        errors = new ArrayList<>();
        checkTargetId(changeTargetNameRequest);
        checkTargetName(changeTargetNameRequest);
        return errors;
    }

    private void checkTargetName(ChangeTargetNameRequest changeTargetNameRequest){
        if (isTargetNameEmpty(changeTargetNameRequest)){
            errors.add(createTargetNameIsEmptyError());
        }
    }

    private void checkTargetId(ChangeTargetNameRequest changeTargetNameRequest){
        if (isTargetIdEmpty(changeTargetNameRequest)){
            errors.add(createTargetIdIsEmptyError());
        }
        if (isTargetIdNegative(changeTargetNameRequest)){
            errors.add(createTargetIdIsNegativeError());
        }
    }

    private CoreError createTargetIdIsNegativeError(){
        return new CoreError("Target ID","must not be negative!");
    }

    private CoreError createTargetIdIsEmptyError(){
        return new CoreError("Target ID","must not be empty!");
    }

    private CoreError createTargetNameIsEmptyError(){
        return new CoreError("Target new name","must not be empty!");
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
