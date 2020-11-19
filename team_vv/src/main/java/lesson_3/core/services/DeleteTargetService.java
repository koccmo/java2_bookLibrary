package lesson_3.core.services;

import lesson_3.core.database.Database;
import lesson_3.core.requests.DeleteTargetRequest;
import lesson_3.core.responses.CoreError;
import lesson_3.core.responses.DeleteTargetResponse;
import lesson_3.core.services.validators.DeleteTargetValidator;

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
