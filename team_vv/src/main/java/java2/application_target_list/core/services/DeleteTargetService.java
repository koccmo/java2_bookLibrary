package java2.application_target_list.core.services;

import java2.application_target_list.core.database.Database;
import java2.application_target_list.core.requests.DeleteTargetRequest;
import java2.application_target_list.core.validators.DeleteTargetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.DeleteTargetResponse;


import java.util.List;

@Component
public class DeleteTargetService {

    @Autowired private Database database;
    @Autowired private DeleteTargetValidator validator;

    public DeleteTargetResponse execute(DeleteTargetRequest request){
        List<CoreError> errors = validator.validate(request, database);

        if (!errors.isEmpty()) {
            return new DeleteTargetResponse(errors);
        }

        database.deleteTarget(request.getTargetIdToDelete());
        return new DeleteTargetResponse(request.getTargetIdToDelete());
    }

}
