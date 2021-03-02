package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.responses.target.AddTargetResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.target.AddTargetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AddTargetService {

    @Autowired
    private JpaTargetRepository jpaTargetRepository;
    @Autowired
    private AddTargetValidator validator;

    private List<CoreError> errors;
    private Target target;

    public AddTargetResponse execute(AddTargetRequest addTargetRequest){
        errors = checkRequestForErrors(addTargetRequest);

        if (requestHaveErrors()) {
            return createAddTargetResponseWithErrors();
        }

        target = createTarget(addTargetRequest);
        addTargetToDB();
        return createAddTargetResponse();
    }

    private AddTargetResponse createAddTargetResponse() {
        return new AddTargetResponse(target);
    }

    private void addTargetToDB(){
        jpaTargetRepository.save(target);
    }

    private Target createTarget(AddTargetRequest addTargetRequest){
        return new Target(addTargetRequest.getName(), addTargetRequest.getDescription(), addTargetRequest.getDeadline());
    }

    private AddTargetResponse createAddTargetResponseWithErrors() {
        return new AddTargetResponse(errors);
    }

    private boolean requestHaveErrors(){
        return !errors.isEmpty();
    }

    private List<CoreError> checkRequestForErrors(AddTargetRequest addTargetRequest){
        return validator.validate(addTargetRequest);
    }

}
