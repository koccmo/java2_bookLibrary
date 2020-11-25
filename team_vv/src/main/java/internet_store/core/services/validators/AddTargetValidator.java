package internet_store.core.services.validators;

import internet_store.core.requests.AddTargetRequest;
import internet_store.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class AddTargetValidator {

    public List<CoreError> validate(AddTargetRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (isTargetNameEmpty(request)){
            errors.add(new CoreError("Target name","Must not be empty!"));
        }

        if (isTargetDescriptionEmpty(request)){
            errors.add(new CoreError("Target description", "Must not be empty!"));
        }

        if (isDeadlineNegative(request)){
            errors.add(new CoreError("Target deadline", "Must not be negative!"));
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
