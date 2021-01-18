package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.target.TargetDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java2.application_target_list.core.requests.target.ChangeTargetDeadlineRequest;
import java2.application_target_list.core.responses.target.ChangeTargetDeadlineResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.target.ChangeTargetDeadlineValidator;



import java.util.List;

@Component
public class ChangeTargetDeadlineService {

    @Autowired private TargetDatabase targetDatabase;
    @Autowired private ChangeTargetDeadlineValidator validator;

    public ChangeTargetDeadlineResponse execute(ChangeTargetDeadlineRequest request){
        List<CoreError> errors = validator.validate(request, targetDatabase);

        if (!errors.isEmpty()) {
            return new ChangeTargetDeadlineResponse(errors);
        }

        targetDatabase.changeTargetDeadline(request.getTargetIdToChange(), request.getNewTargetDeadline());
        return new ChangeTargetDeadlineResponse(request.getTargetIdToChange(), request.getNewTargetDeadline());
    }
}
