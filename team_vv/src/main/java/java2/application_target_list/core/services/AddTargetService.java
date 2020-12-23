package java2.application_target_list.core.services;

import java2.application_target_list.core.database.Database;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.requests.AddTargetRequest;
import java2.application_target_list.core.responses.AddTargetResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.AddTargetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class AddTargetService {

    @Autowired private Database database;
    @Autowired private AddTargetValidator validator;

    public AddTargetResponse execute(AddTargetRequest request){
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new AddTargetResponse(errors);
        }

        Target target = new Target(request.getName(), request.getDescription(), request.getDeadline());
        database.addTarget(target);
        return new AddTargetResponse(target);
    }

}
