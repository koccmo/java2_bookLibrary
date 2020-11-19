package lesson_3.core.services;

import lesson_3.Target;
import lesson_3.core.database.Database;
import lesson_3.core.requests.AddTargetRequest;
import lesson_3.core.responses.AddTargetResponse;
import lesson_3.core.responses.CoreError;
import lesson_3.core.services.validators.AddTargetValidator;

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
