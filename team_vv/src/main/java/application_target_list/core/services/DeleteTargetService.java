package application_target_list.core.services;

import application_target_list.core.database.Database;
import application_target_list.core.requests.DeleteTargetRequest;
import application_target_list.core.responses.CoreError;
import application_target_list.core.responses.DeleteTargetResponse;
import application_target_list.core.validators.DeleteTargetValidator;
import application_target_list.dependency_injection.DIComponent;
import application_target_list.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class DeleteTargetService {

    @DIDependency private Database database;
    @DIDependency private DeleteTargetValidator validator;

    public DeleteTargetResponse execute(DeleteTargetRequest request){
        List<CoreError> errors = validator.validate(request, database);

        if (!errors.isEmpty()) {
            return new DeleteTargetResponse(errors);
        }

        database.deleteTarget(request.getTargetIdToDelete());
        return new DeleteTargetResponse(request.getTargetIdToDelete());
    }

}
