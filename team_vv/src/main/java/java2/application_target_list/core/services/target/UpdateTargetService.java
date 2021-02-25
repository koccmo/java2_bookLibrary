package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.target.UpdateTargetRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.target.UpdateTargetResponse;
import java2.application_target_list.core.validators.target.UpdateTargetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@Component
@Service
@Transactional
public class UpdateTargetService {

    @Autowired private UpdateTargetValidator updateTargetValidator;
    @Autowired private JpaTargetRepository jpaTargetRepository;

    public UpdateTargetResponse execute(UpdateTargetRequest updateTargetRequest) {
        List<CoreError> errors = updateTargetValidator.validate(updateTargetRequest);


        if (!jpaTargetRepository.existsById(updateTargetRequest.getTargetIdToChange())){
            errors.add(new CoreError("Target ID;","no target with that ID"));
        }

        if (!errors.isEmpty()) {
            return new UpdateTargetResponse(errors);
        }

        jpaTargetRepository.changeTargetName(updateTargetRequest.getTargetIdToChange(), updateTargetRequest.getNewTargetName());
        jpaTargetRepository.changeTargetDescription(updateTargetRequest.getTargetIdToChange(), updateTargetRequest.getNewTargetDescription());
        jpaTargetRepository.changeTargetDeadline(updateTargetRequest.getTargetIdToChange(), updateTargetRequest.getNewTargetDeadline());

        return new UpdateTargetResponse(updateTargetRequest.getTargetIdToChange(),
                updateTargetRequest.getNewTargetName(),
                updateTargetRequest.getNewTargetDescription(),
                updateTargetRequest.getNewTargetDeadline());
    }
}
