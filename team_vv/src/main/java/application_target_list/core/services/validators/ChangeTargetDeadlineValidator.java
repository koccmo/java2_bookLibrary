package application_target_list.core.services.validators;

import application_target_list.core.database.Database;
import application_target_list.core.requests.ChangeTargetDeadlineRequest;
import application_target_list.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class ChangeTargetDeadlineValidator {

    public List<CoreError> validate(ChangeTargetDeadlineRequest request, Database database) {

        List<CoreError> errors = new ArrayList<>();

        if (!isIdInTargetList(request,database)){
            errors.add(new CoreError("Target ID;","no target with that ID"));
        }

        if (isTargetIdEmpty(request)){
            errors.add(new CoreError("Target ID","must not be empty!"));
        }
        if (isTargetIdNegative(request)){
            errors.add(new CoreError("Target ID","must not be negative!"));
        }
        if (isDeadlineNegative(request)){
            errors.add(new CoreError("Target new deadline", "must not be negative!"));
        }

        return errors;
    }

    private boolean isIdInTargetList(ChangeTargetDeadlineRequest request, Database database){
        for (int i = 0; i < database.getTargetsList().size(); i++){
            return database.getTargetsList().get(i).getId().equals(request.getTargetIdToChange());
        }
        return false;
    }


    private boolean isTargetIdEmpty(ChangeTargetDeadlineRequest request) {
        return request.getTargetIdToChange() == null;
    }

    private boolean isTargetIdNegative(ChangeTargetDeadlineRequest request){
        return request.getTargetIdToChange() < 0;
    }
    private boolean isDeadlineNegative(ChangeTargetDeadlineRequest request){
        return request.getNewTargetDeadline() < 0;
    }

}
