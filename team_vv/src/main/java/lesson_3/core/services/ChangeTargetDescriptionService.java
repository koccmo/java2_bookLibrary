package lesson_3.core.services;

import lesson_3.core.database.Database;
import lesson_3.core.requests.ChangeTargetDescriptionRequest;
import lesson_3.core.responses.ChangeTargetDescriptionResponse;
import lesson_3.core.responses.CoreError;
import lesson_3.core.services.validators.ChangeTargetDescriptionValidator;

import java.util.List;

public class ChangeTargetDescriptionService {
    private Database database;
    private ChangeTargetDescriptionValidator validator;

    public ChangeTargetDescriptionService(Database database, ChangeTargetDescriptionValidator validator){
        this.database = database;
        this.validator = validator;
    }

    public ChangeTargetDescriptionResponse execute(ChangeTargetDescriptionRequest request){
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new ChangeTargetDescriptionResponse(errors);
        }
        database.changeTargetDescription(request.getTargetIdTOChange(), request.getNewTargetDescription());
        return new ChangeTargetDescriptionResponse(request.getTargetIdTOChange(), request.getNewTargetDescription());
    }
}
