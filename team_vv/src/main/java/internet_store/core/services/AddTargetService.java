package internet_store.core.services;

import internet_store.Target;
import internet_store.core.database.Database;
import internet_store.core.requests.AddTargetRequest;
import internet_store.core.responses.AddTargetResponse;
import internet_store.core.responses.CoreError;
import internet_store.core.services.validators.AddTargetValidator;

import java.util.List;

public class AddTargetService {

    private Database database;
    private AddTargetValidator validator;

    public AddTargetService(Database database, AddTargetValidator validator){
        this.database = database;
        this.validator = validator;
    }

    public AddTargetResponse execute(AddTargetRequest request){
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new AddTargetResponse(errors);
        }

        Target target = new Target(request.getName(), request.getDescription(), request.getDeadline());
        database.addTarget(target);
        return new AddTargetResponse(target);
    }
}
