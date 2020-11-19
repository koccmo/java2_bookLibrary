package lesson_3.core.services;

import lesson_3.core.database.Database;
import lesson_3.core.requests.ChangeTargetNameRequest;
import lesson_3.core.responses.ChangeTargetNameResponse;
import lesson_3.core.responses.CoreError;
import lesson_3.core.services.validators.ChangeTargetNameValidator;

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
