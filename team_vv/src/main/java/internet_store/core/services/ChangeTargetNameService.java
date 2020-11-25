package internet_store.core.services;

import internet_store.core.database.Database;
import internet_store.core.requests.ChangeTargetNameRequest;
import internet_store.core.responses.ChangeTargetNameResponse;
import internet_store.core.responses.CoreError;
import internet_store.core.services.validators.ChangeTargetNameValidator;

import java.util.List;

public class ChangeTargetNameService {

    private Database database;
    private ChangeTargetNameValidator validator;

    public ChangeTargetNameService(Database database, ChangeTargetNameValidator validator){
        this.database = database;
        this.validator = validator;
    }

    public ChangeTargetNameResponse execute(ChangeTargetNameRequest request){
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new ChangeTargetNameResponse(errors);
        }

        database.changeTargetName(request.getTargetIdToChange(), request.getNewTargetName());
        return new ChangeTargetNameResponse(request.getTargetIdToChange(), request.getNewTargetName());
    }
}
