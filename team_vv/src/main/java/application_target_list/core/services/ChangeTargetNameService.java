package application_target_list.core.services;

import application_target_list.core.database.Database;
import application_target_list.core.requests.ChangeTargetNameRequest;
import application_target_list.core.responses.ChangeTargetNameResponse;
import application_target_list.core.responses.CoreError;
import application_target_list.core.services.validators.ChangeTargetNameValidator;

import java.util.List;

public class ChangeTargetNameService {

    private Database database;
    private ChangeTargetNameValidator validator;

    public ChangeTargetNameService(Database database, ChangeTargetNameValidator validator){
        this.database = database;
        this.validator = validator;
    }

    public ChangeTargetNameResponse execute(ChangeTargetNameRequest request){
        List<CoreError> errors = validator.validate(request, database);

        if (!errors.isEmpty()) {
            return new ChangeTargetNameResponse(errors);
        }

        database.changeTargetName(request.getTargetIdToChange(), request.getNewTargetName());
        return new ChangeTargetNameResponse(request.getTargetIdToChange(), request.getNewTargetName());
    }
}
