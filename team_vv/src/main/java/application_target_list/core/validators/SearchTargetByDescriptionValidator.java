package application_target_list.core.validators;

import application_target_list.core.requests.SearchTargetByDescriptionRequest;
import application_target_list.core.responses.CoreError;
import java.util.ArrayList;
import java.util.List;

public class SearchTargetByDescriptionValidator {

    public List<CoreError> validate(SearchTargetByDescriptionRequest request){
        List<CoreError> errors = new ArrayList<>();

        if (isTargetNameEmpty(request)) {
            errors.add(new CoreError("Target description", "must not be empty!"));
        }

        return errors;
    }

    private boolean isTargetNameEmpty(SearchTargetByDescriptionRequest request) {
        return request.getDescription() == null || request.getDescription().isEmpty();
    }
}
