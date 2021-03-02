package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.requests.target.UpdateTargetRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateTargetValidator {

    private List<CoreError> errors;

    public List<CoreError> validate(UpdateTargetRequest updateTargetRequest) {
        errors = new ArrayList<>();
        checkTargetId(updateTargetRequest);
        checkTargetName(updateTargetRequest);
        checkTargetDescription(updateTargetRequest);
        checkTargetDeadline(updateTargetRequest);
        return errors;
    }

    private void checkTargetName(UpdateTargetRequest updateTargetRequest){
        if (isTargetNameEmpty(updateTargetRequest)){
            errors.add(createTargetNameIsEmptyError());
        }
    }

    private void checkTargetDeadline(UpdateTargetRequest updateTargetRequest){
        if (isDeadlineNegative(updateTargetRequest)){
            errors.add(createTargetDeadlineIsNegativeError());
        }

        if (isDeadlineEmpty(updateTargetRequest)){
            errors.add(createTargetDeadlineIsNegativeError());
        }
    }

    private void checkTargetDescription(UpdateTargetRequest updateTargetRequest){
        if (isTargetDescriptionEmpty(updateTargetRequest)){
            errors.add(createTargetDescriptionIsEmptyError());
        }
    }

    private void checkTargetId(UpdateTargetRequest updateTargetRequest){
        if (isTargetIdEmpty(updateTargetRequest)){
            errors.add(createTargetIdIsEmptyError());
        }
        if (isTargetIdNegative(updateTargetRequest)){
            errors.add(createTargetIdIsNegativeError());
        }
    }

    private CoreError createTargetDeadlineIsEmptyError(){
        return new CoreError("Target new deadline", "must not be empty!");
    }

    private CoreError createTargetDeadlineIsNegativeError(){
        return new CoreError("Target new deadline", "must not be negative!");
    }

    private CoreError createTargetDescriptionIsEmptyError(){
        return new CoreError("Target new description","must not be empty!");
    }

    private CoreError createTargetNameIsEmptyError(){
        return new CoreError("Target new name","must not be empty!");
    }

    private CoreError createTargetIdIsNegativeError(){
        return new CoreError("Target ID","must not be negative!");
    }

    private CoreError createTargetIdIsEmptyError(){
        return new CoreError("Target ID","must not be empty!");
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
