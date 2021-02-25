package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java2.application_target_list.core.requests.target.ChangeTargetDeadlineRequest;
import java2.application_target_list.core.responses.target.ChangeTargetDeadlineResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.target.ChangeTargetDeadlineValidator;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@Component
@Service
@Transactional
public class ChangeTargetDeadlineService {

    @Autowired private JpaTargetRepository jpaTargetRepository;
    @Autowired private ChangeTargetDeadlineValidator validator;

    public ChangeTargetDeadlineResponse execute(ChangeTargetDeadlineRequest request){

        List<CoreError> errors = validator.validate(request);

        if (!jpaTargetRepository.existsById(request.getTargetIdToChange())){
            errors.add(new CoreError("Target ID;","no target with that ID"));
        }

        if (!errors.isEmpty()) {
            return new ChangeTargetDeadlineResponse(errors);
        }

        jpaTargetRepository.changeTargetDeadline(request.getTargetIdToChange(), request.getNewTargetDeadline());
        return new ChangeTargetDeadlineResponse(request.getTargetIdToChange(), request.getNewTargetDeadline());
    }
}
