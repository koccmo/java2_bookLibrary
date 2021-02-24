package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.target.ChangeTargetDescriptionRequest;
import java2.application_target_list.core.responses.target.ChangeTargetDescriptionResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.target.ChangeTargetDescriptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@Component
@Service
@Transactional
public class ChangeTargetDescriptionService {

    @Autowired private JpaTargetRepository jpaTargetRepository;
    @Autowired private ChangeTargetDescriptionValidator validator;

    public ChangeTargetDescriptionResponse execute(ChangeTargetDescriptionRequest request){

        List<CoreError> errors = validator.validate(request);

        if (!jpaTargetRepository.existsById(request.getTargetIdToChange())) {
            errors.add(new CoreError("Target ID;", "no target with that ID"));
        }

        if (!errors.isEmpty()) {
            return new ChangeTargetDescriptionResponse(errors);
        }

        jpaTargetRepository.changeTargetDescription(request.getTargetIdToChange(), request.getNewTargetDescription());
        return new ChangeTargetDescriptionResponse(request.getTargetIdToChange(), request.getNewTargetDescription());
    }
}
