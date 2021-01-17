package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.target.TargetDatabase;
import java2.application_target_list.core.requests.target.ChangeTargetNameRequest;
import java2.application_target_list.core.validators.target.ChangeTargetNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java2.application_target_list.core.responses.target.ChangeTargetNameResponse;
import java2.application_target_list.core.responses.CoreError;


import java.util.List;

@Component
public class ChangeTargetNameService {

    @Autowired private TargetDatabase targetDatabase;
    @Autowired private ChangeTargetNameValidator validator;

    public ChangeTargetNameResponse execute(ChangeTargetNameRequest request){
        List<CoreError> errors = validator.validate(request, targetDatabase);

        if (!errors.isEmpty()) {
            return new ChangeTargetNameResponse(errors);
        }

        targetDatabase.changeTargetName(request.getTargetIdToChange(), request.getNewTargetName());
        return new ChangeTargetNameResponse(request.getTargetIdToChange(), request.getNewTargetName());
    }
}
