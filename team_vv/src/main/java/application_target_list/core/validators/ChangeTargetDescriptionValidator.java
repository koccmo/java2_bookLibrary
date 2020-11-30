package application_target_list.core.validators;

import application_target_list.core.database.Database;
import application_target_list.core.requests.ChangeTargetDescriptionRequest;
import application_target_list.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class ChangeTargetDescriptionValidator {

    public List<CoreError> validate(ChangeTargetDescriptionRequest request, Database database) {
        List<CoreError> errors = new ArrayList<>();

        if (!database.isIdInTargetList(request.getTargetIdToChange())){
            errors.add(new CoreError("Target ID;","no target with that ID"));
        }


        if (isTargetIdEmpty(request)){
            errors.add(new CoreError("Target ID","must not be empty!"));
        }
        if (isTargetIdNegative(request)){
            errors.add(new CoreError("Target ID","must not be negative!"));
        }

        if (isTargetDescriptionEmpty(request)){
            errors.add(new CoreError("Target new description","must not be empty!"));
        }

        return errors;
    }

    private boolean isTargetDescriptionEmpty(ChangeTargetDescriptionRequest request) {
        return request.getNewTargetDescription() == null || request.getNewTargetDescription().isEmpty();
    }

    private boolean isTargetIdEmpty(ChangeTargetDescriptionRequest request) {
        return request.getTargetIdToChange() == null;
    }

    private boolean isTargetIdNegative(ChangeTargetDescriptionRequest request){
        return request.getTargetIdToChange() < 0;
    }

}
