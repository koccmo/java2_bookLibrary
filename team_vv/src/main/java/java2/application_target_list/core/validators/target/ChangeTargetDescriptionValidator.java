package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.requests.target.ChangeTargetDescriptionRequest;
import org.springframework.stereotype.Component;
import java2.application_target_list.core.responses.CoreError;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeTargetDescriptionValidator {

    public List<CoreError> validate(ChangeTargetDescriptionRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (isTargetIdEmpty(request)) {
            errors.add(new CoreError("Target ID", "must not be empty!"));
        }
        if (isTargetIdNegative(request)) {
            errors.add(new CoreError("Target ID", "must not be negative!"));
        }

        if (isTargetDescriptionEmpty(request)) {
            errors.add(new CoreError("Target new description", "must not be empty!"));
        }

        return errors;
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
