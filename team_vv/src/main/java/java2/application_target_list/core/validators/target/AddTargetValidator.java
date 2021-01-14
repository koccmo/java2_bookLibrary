package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.requests.target.AddTargetRequest;
import org.springframework.stereotype.Component;

import java2.application_target_list.core.responses.CoreError;
import java.util.ArrayList;
import java.util.List;

@Component
public class AddTargetValidator {

    public List<CoreError> validate(AddTargetRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (isTargetNameEmpty(request)){
            errors.add(new CoreError("Target name","must not be empty!"));
        }

        if (isTargetDescriptionEmpty(request)){
            errors.add(new CoreError("Target description", "must not be empty!"));
        }

        if (isDeadlineNegative(request)){
            errors.add(new CoreError("Target deadline", "must not be negative!"));
        }

        return errors;
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
