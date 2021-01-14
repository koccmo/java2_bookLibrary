package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.TargetDatabase;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.responses.target.AddTargetResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.target.AddTargetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class AddTargetService {

    @Autowired private TargetDatabase targetDatabase;
    @Autowired private AddTargetValidator validator;

    public AddTargetResponse execute(AddTargetRequest request){
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new AddTargetResponse(errors);
        }

        Target target = new Target(request.getName(), request.getDescription(), request.getDeadline());
        targetDatabase.addTarget(target);
        return new AddTargetResponse(target);
    }

}
