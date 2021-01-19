package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.target.TargetDatabase;
import java2.application_target_list.core.requests.target.DeleteTargetRequest;
import java2.application_target_list.core.validators.target.DeleteTargetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.target.DeleteTargetResponse;


import java.util.List;

@Component
public class DeleteTargetService {

    @Autowired private TargetDatabase targetDatabase;
    @Autowired private DeleteTargetValidator validator;

    public DeleteTargetResponse execute(DeleteTargetRequest request){
        List<CoreError> errors = validator.validate(request, targetDatabase);

        if (!errors.isEmpty()) {
            return new DeleteTargetResponse(errors);
        }

        targetDatabase.deleteTarget(request.getTargetIdToDelete());
        return new DeleteTargetResponse(request.getTargetIdToDelete());
    }

}
