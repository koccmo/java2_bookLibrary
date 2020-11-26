package application_target_list.core.services;

import application_target_list.core.database.Target;
import application_target_list.core.database.Database;
import application_target_list.core.requests.AddTargetRequest;
import application_target_list.core.responses.AddTargetResponse;
import application_target_list.core.responses.CoreError;
import application_target_list.core.validators.AddTargetValidator;

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
