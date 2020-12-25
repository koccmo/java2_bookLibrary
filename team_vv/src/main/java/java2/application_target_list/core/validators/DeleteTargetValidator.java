package java2.application_target_list.core.validators;

import java2.application_target_list.core.database.Database;
import java2.application_target_list.core.requests.DeleteTargetRequest;
import org.springframework.stereotype.Component;

import java2.application_target_list.core.responses.CoreError;
import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteTargetValidator {

    public List<CoreError> validate(DeleteTargetRequest request, Database database) {
        List<CoreError> errors = new ArrayList<>();


        if (!database.isIdInTargetList(request.getTargetIdToDelete())){
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


    private boolean isTargetIdEmpty(DeleteTargetRequest request) {
        return request.getTargetIdToDelete() == null;
    }

    private boolean isTargetIdNegative(DeleteTargetRequest request){
        return request.getTargetIdToDelete() < 0;
    }

}
