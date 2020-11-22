package application_target_list.core.validators;


import application_target_list.core.requests.SearchTargetByNameRequest;
import application_target_list.core.responses.CoreError;
import java.util.ArrayList;
import java.util.List;

public class SearchTargetByNameValidator {

    public List<CoreError> validate(SearchTargetByNameRequest request){
        List<CoreError> errors = new ArrayList<>();

        if (isTargetNameEmpty(request)) {
            errors.add(new CoreError("Target name", "must not be empty!"));
        }

        return errors;
    }

    private boolean isTargetNameEmpty(SearchTargetByNameRequest request) {
        return request.getName() == null || request.getName().isEmpty();
    }



}
