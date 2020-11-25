package internet_store.core.services;

import internet_store.core.database.Database;
import internet_store.core.requests.ChangeTargetDescriptionRequest;
import internet_store.core.responses.ChangeTargetDescriptionResponse;
import internet_store.core.responses.CoreError;
import internet_store.core.services.validators.ChangeTargetDescriptionValidator;

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
