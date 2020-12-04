package application_target_list.core.services;

import application_target_list.core.database.Database;
import application_target_list.core.requests.ChangeTargetDeadlineRequest;
import application_target_list.core.responses.ChangeTargetDeadlineResponse;
import application_target_list.core.responses.CoreError;
import application_target_list.core.validators.ChangeTargetDeadlineValidator;
import application_target_list.dependency_injection.DIComponent;
import application_target_list.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class ChangeTargetDeadlineService {

    @DIDependency private Database database;
    @DIDependency private ChangeTargetDeadlineValidator validator;

    public ChangeTargetDeadlineResponse execute(ChangeTargetDeadlineRequest request){
        List<CoreError> errors = validator.validate(request, database);

        if (!errors.isEmpty()) {
            return new ChangeTargetDeadlineResponse(errors);
        }

        database.changeTargetDeadline(request.getTargetIdToChange(), request.getNewTargetDeadline());
        return new ChangeTargetDeadlineResponse(request.getTargetIdToChange(), request.getNewTargetDeadline());
    }
}
