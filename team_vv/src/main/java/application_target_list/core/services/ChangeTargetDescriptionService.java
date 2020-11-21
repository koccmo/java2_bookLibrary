package application_target_list.core.services;

import application_target_list.core.database.Database;
import application_target_list.core.requests.ChangeTargetDescriptionRequest;
import application_target_list.core.responses.ChangeTargetDescriptionResponse;
import application_target_list.core.responses.CoreError;
import application_target_list.core.services.validators.ChangeTargetDescriptionValidator;

import java.util.List;

public class ChangeTargetDescriptionService {
    private Database database;
    private ChangeTargetDescriptionValidator validator;

    public ChangeTargetDescriptionService(Database database, ChangeTargetDescriptionValidator validator){
        this.database = database;
        this.validator = validator;
    }

    public ChangeTargetDescriptionResponse execute(ChangeTargetDescriptionRequest request){
        List<CoreError> errors = validator.validate(request, database);

        if (!errors.isEmpty()) {
            return new ChangeTargetDescriptionResponse(errors);
        }
        database.changeTargetDescription(request.getTargetIdToChange(), request.getNewTargetDescription());
        return new ChangeTargetDescriptionResponse(request.getTargetIdToChange(), request.getNewTargetDescription());
    }
}
