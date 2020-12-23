package java2.application_target_list.core.services;

import java2.application_target_list.core.database.Database;
import java2.application_target_list.core.requests.ChangeTargetDescriptionRequest;
import java2.application_target_list.core.responses.ChangeTargetDescriptionResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.ChangeTargetDescriptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class ChangeTargetDescriptionService {

    @Autowired private Database database;
    @Autowired private ChangeTargetDescriptionValidator validator;

    public ChangeTargetDescriptionResponse execute(ChangeTargetDescriptionRequest request){
        List<CoreError> errors = validator.validate(request, database);

        if (!errors.isEmpty()) {
            return new ChangeTargetDescriptionResponse(errors);
        }
        database.changeTargetDescription(request.getTargetIdToChange(), request.getNewTargetDescription());
        return new ChangeTargetDescriptionResponse(request.getTargetIdToChange(), request.getNewTargetDescription());
    }
}
