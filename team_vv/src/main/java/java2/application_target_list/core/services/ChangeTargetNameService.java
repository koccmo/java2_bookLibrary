package java2.application_target_list.core.services;

import java2.application_target_list.core.database.Database;
import java2.application_target_list.core.requests.ChangeTargetNameRequest;
import java2.application_target_list.core.validators.ChangeTargetNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java2.application_target_list.core.responses.ChangeTargetNameResponse;
import java2.application_target_list.core.responses.CoreError;


import java.util.List;

@Component
public class ChangeTargetNameService {

    @Autowired private Database database;
    @Autowired private ChangeTargetNameValidator validator;

    public ChangeTargetNameResponse execute(ChangeTargetNameRequest request){
        List<CoreError> errors = validator.validate(request, database);

        if (!errors.isEmpty()) {
            return new ChangeTargetNameResponse(errors);
        }

        database.changeTargetName(request.getTargetIdToChange(), request.getNewTargetName());
        return new ChangeTargetNameResponse(request.getTargetIdToChange(), request.getNewTargetName());
    }
}
