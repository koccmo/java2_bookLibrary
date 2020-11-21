package application_target_list.core.services;

import application_target_list.core.database.Database;
import application_target_list.core.requests.ChangeTargetDeadlineRequest;
import application_target_list.core.responses.ChangeTargetDeadlineResponse;
import application_target_list.core.responses.ChangeTargetDescriptionResponse;
import application_target_list.core.responses.CoreError;
import application_target_list.core.services.validators.ChangeTargetDeadlineValidator;

import java.util.List;

public class ChangeTargetDeadlineService {

    private Database database;
    private ChangeTargetDeadlineValidator validator;

    public ChangeTargetDeadlineService(Database database, ChangeTargetDeadlineValidator validator){
        this.database = database;
        this.validator = validator;
    }

    public ChangeTargetDeadlineResponse execute(ChangeTargetDeadlineRequest request){
        List<CoreError> errors = validator.validate(request, database);

        if (!errors.isEmpty()) {
            return new ChangeTargetDeadlineResponse(errors);
        }

        database.changeTargetDeadline(request.getTargetIdToChange(), request.getNewTargetDeadline());
        return new ChangeTargetDeadlineResponse(request.getTargetIdToChange(), request.getNewTargetDeadline());
    }
}
