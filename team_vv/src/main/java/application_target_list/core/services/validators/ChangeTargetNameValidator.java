package application_target_list.core.services.validators;

import application_target_list.core.database.Database;
import application_target_list.core.requests.ChangeTargetNameRequest;
import application_target_list.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class ChangeTargetNameValidator {

    public List<CoreError> validate(ChangeTargetNameRequest request, Database database) {
        List<CoreError> errors = new ArrayList<>();

        if (!isIdInTargetList(request,database)){
            errors.add(new CoreError("Target ID;","no target with that ID"));
        }

        if (isTargetNameEmpty(request)){
            errors.add(new CoreError("Target new name","must not be empty!"));
        }

        if (isTargetIdEmpty(request)){
            errors.add(new CoreError("Target ID","must not be empty!"));
        }
        if (isTargetIdNegative(request)){
            errors.add(new CoreError("Target ID","must not be negative!"));
        }

        return errors;
    }

    private boolean isIdInTargetList(ChangeTargetNameRequest request, Database database){
        for (int i = 0; i < database.getTargetsList().size(); i++){
            return database.getTargetsList().get(i).getId().equals(request.getTargetIdToChange());
        }
        return false;
    }

    private boolean isTargetNameEmpty(ChangeTargetNameRequest request) {
        return request.getNewTargetName() == null || request.getNewTargetName().isEmpty();
    }

    private boolean isTargetIdEmpty(ChangeTargetNameRequest request) {
        return request.getTargetIdToChange() == null;
    }

    private boolean isTargetIdNegative(ChangeTargetNameRequest request){
        return request.getTargetIdToChange() < 0;
    }


}
