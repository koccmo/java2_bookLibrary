package application_target_list.core.services.validators;


import application_target_list.core.database.Database;
import application_target_list.core.requests.DeleteTargetRequest;
import application_target_list.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class DeleteTargetValidator {

    public List<CoreError> validate(DeleteTargetRequest request, Database database) {
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

        return errors;
    }

    private boolean isIdInTargetList(DeleteTargetRequest request, Database database){
        for (int i = 0; i < database.getTargetsList().size(); i++){
            return database.getTargetsList().get(i).getId().equals(request.getTargetIdToDelete());
        }
        return false;
    }


    private boolean isTargetIdEmpty(DeleteTargetRequest request) {
        return request.getTargetIdToDelete() == null;
    }

    private boolean isTargetIdNegative(DeleteTargetRequest request){
        return request.getTargetIdToDelete() < 0;
    }

}
