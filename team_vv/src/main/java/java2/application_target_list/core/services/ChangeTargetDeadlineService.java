package java2.application_target_list.core.services;

import java2.application_target_list.core.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java2.application_target_list.core.requests.ChangeTargetDeadlineRequest;
import java2.application_target_list.core.responses.ChangeTargetDeadlineResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.ChangeTargetDeadlineValidator;



import java.util.List;

@Component
public class ChangeTargetDeadlineService {

    @Autowired private Database database;
    @Autowired private ChangeTargetDeadlineValidator validator;

    public ChangeTargetDeadlineResponse execute(ChangeTargetDeadlineRequest request){
        List<CoreError> errors = validator.validate(request, database);

        if (!errors.isEmpty()) {
            return new ChangeTargetDeadlineResponse(errors);
        }

        database.changeTargetDeadline(request.getTargetIdToChange(), request.getNewTargetDeadline());
        return new ChangeTargetDeadlineResponse(request.getTargetIdToChange(), request.getNewTargetDeadline());
    }
}
