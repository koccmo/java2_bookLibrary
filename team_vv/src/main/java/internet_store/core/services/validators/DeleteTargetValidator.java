package internet_store.core.services.validators;


import internet_store.core.requests.DeleteTargetRequest;
import internet_store.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class DeleteTargetValidator {

    public List<CoreError> validate(DeleteTargetRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (isTargetIdEmpty(request)){
            errors.add(new CoreError("Target ID","Must not be empty!"));
        }
        if (isTargetIdNegative(request)){
            errors.add(new CoreError("Target ID","Must not be negative!"));
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
