package application_target_list.core.services;

import application_target_list.core.database.Database;
import application_target_list.core.requests.ChangeTargetNameRequest;
import application_target_list.core.responses.ChangeTargetNameResponse;
import application_target_list.core.responses.CoreError;
import application_target_list.core.validators.ChangeTargetNameValidator;
import application_target_list.dependency_injection.DIComponent;
import application_target_list.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class ChangeTargetNameService {

    @DIDependency private Database database;
    @DIDependency private ChangeTargetNameValidator validator;

    public ChangeTargetNameResponse execute(ChangeTargetNameRequest request){
        List<CoreError> errors = validator.validate(request, database);

        if (!errors.isEmpty()) {
            return new ChangeTargetNameResponse(errors);
        }

        database.changeTargetName(request.getTargetIdToChange(), request.getNewTargetName());
        return new ChangeTargetNameResponse(request.getTargetIdToChange(), request.getNewTargetName());
    }
}
