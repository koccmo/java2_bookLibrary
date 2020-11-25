package internet_store.core.services;

import internet_store.core.database.Database;
import internet_store.core.requests.ChangeTargetDeadlineRequest;
import internet_store.core.responses.ChangeTargetDeadlineResponse;
import internet_store.core.responses.CoreError;
import internet_store.core.services.validators.ChangeTargetDeadlineValidator;

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
