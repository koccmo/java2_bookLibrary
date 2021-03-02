package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.requests.target.AddTargetRequest;
import org.springframework.stereotype.Component;
import java2.application_target_list.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddTargetValidator {

    private List<CoreError> errors;

    public List<CoreError> validate(AddTargetRequest addTargetRequest) {
        errors = new ArrayList<>();
        checkTargetName(addTargetRequest);
        checkTargetDescription(addTargetRequest);
        checkTargetDeadline(addTargetRequest);
        return errors;
    }

    private void checkTargetDeadline(AddTargetRequest addTargetRequest){
        if (isDeadlineNegative(addTargetRequest)) {
            errors.add(createTargetDeadlineIsNegativeError());
        }
    }

    private void checkTargetDescription(AddTargetRequest addTargetRequest){
        if (isTargetDescriptionEmpty(addTargetRequest)){
            errors.add(createTargetDescriptionIsEmptyError());
        }
    }

    private void checkTargetName(AddTargetRequest addTargetRequest){
        if (isTargetNameEmpty(addTargetRequest)){
            errors.add(createTargetNameIsEmptyError());
        }
    }

    private CoreError createTargetDeadlineIsNegativeError(){
        return new CoreError("Target deadline", "must not be negative!");
    }

    private CoreError createTargetDescriptionIsEmptyError(){
        return new CoreError("Target description", "must not be empty!");
    }

    private CoreError createTargetNameIsEmptyError(){
        return new CoreError("Target name","must not be empty!");
    }

    private boolean isTargetNameEmpty(AddTargetRequest request) {
        return request.getName() == null || request.getName().isEmpty();
    }

    private boolean isTargetDescriptionEmpty(AddTargetRequest request) {
        return request.getDescription() == null || request.getDescription().isEmpty();
    }

    private boolean isDeadlineNegative(AddTargetRequest request){
        return request.getDeadline() < 0;
    }
}
