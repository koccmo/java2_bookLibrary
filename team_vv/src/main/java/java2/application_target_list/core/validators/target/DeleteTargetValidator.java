package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.requests.target.DeleteTargetRequest;
import org.springframework.stereotype.Component;
import java2.application_target_list.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteTargetValidator {

    private List<CoreError> errors;

    public List<CoreError> validate(DeleteTargetRequest deleteTargetRequest) {
        errors = new ArrayList<>();
        checkTargetId(deleteTargetRequest);
        return errors;
    }

    private void checkTargetId(DeleteTargetRequest deleteTargetRequest){
        if (isTargetIdEmpty(deleteTargetRequest)){
            errors.add(createTargetIdIsEmptyError());
        }
        if (isTargetIdNegative(deleteTargetRequest)){
            errors.add(createTargetIdISNegativeError());
        }
    }

    private CoreError createTargetIdISNegativeError(){
        return new CoreError("Target ID","must not be negative!");
    }

    private CoreError createTargetIdIsEmptyError(){
        return new CoreError("Target ID","must not be empty!");
    }

    private boolean isTargetIdEmpty(DeleteTargetRequest request) {
        return request.getTargetIdToDelete() == null;
    }

    private boolean isTargetIdNegative(DeleteTargetRequest request){
        return request.getTargetIdToDelete() < 0;
    }

}
