package internet_store.core.services;

import internet_store.core.database.Database;
import internet_store.core.requests.DeleteTargetRequest;
import internet_store.core.responses.CoreError;
import internet_store.core.responses.DeleteTargetResponse;
import internet_store.core.services.validators.DeleteTargetValidator;

import java.util.List;

public class DeleteTargetService {
    private Database database;
    private DeleteTargetValidator validator;

    public DeleteTargetService(Database database, DeleteTargetValidator validator){
        this.database = database;
        this.validator = validator;
    }

    public DeleteTargetResponse execute(DeleteTargetRequest request){
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new DeleteTargetResponse(errors);
        }

        database.deleteTarget(request.getTargetIdToDelete());
        return new DeleteTargetResponse(request.getTargetIdToDelete());
    }
}
