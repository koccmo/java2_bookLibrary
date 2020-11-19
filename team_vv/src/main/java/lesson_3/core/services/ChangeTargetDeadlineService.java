package lesson_3.core.services;

import lesson_3.core.database.Database;
import lesson_3.core.requests.ChangeTargetDeadlineRequest;
import lesson_3.core.responses.ChangeTargetDeadlineResponse;
import lesson_3.core.responses.CoreError;
import lesson_3.core.services.validators.ChangeTargetDeadlineValidator;

import java.util.List;

public class ChangeTargetDeadlineService {

    private Database database;
    private ChangeTargetDeadlineValidator validator;

    public ChangeTargetDeadlineService(Database database, ChangeTargetDeadlineValidator validator){
        this.database = database;
        this.validator = validator;
    }

    public ChangeTargetDeadlineResponse execute(ChangeTargetDeadlineRequest request){
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new ChangeTargetDeadlineResponse(errors);
        }

        database.changeTargetDeadline(request.getTargetIdToChange(), request.getNewTargetDeadline());
        return new ChangeTargetDeadlineResponse(request.getTargetIdToChange(), request.getNewTargetDeadline());
    }
}
