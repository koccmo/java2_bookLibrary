package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.target.TargetRepository;
import java2.application_target_list.core.requests.target.UpdateTargetRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.target.UpdateTargetResponse;
import java2.application_target_list.core.validators.target.UpdateTargetValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UpdateTargetService {

    @Autowired private TargetRepository targetRepository;
    @Autowired private UpdateTargetValidator updateTargetValidator;

    public UpdateTargetResponse execute(UpdateTargetRequest updateTargetRequest) {
        List<CoreError> errors = updateTargetValidator.validate(updateTargetRequest, targetRepository);

        if (!errors.isEmpty()) {
            return new UpdateTargetResponse(errors);
        }

        targetRepository.changeTargetName(updateTargetRequest.getTargetIdToChange(), updateTargetRequest.getNewTargetName());
        targetRepository.changeTargetDescription(updateTargetRequest.getTargetIdToChange(), updateTargetRequest.getNewTargetDescription());
        targetRepository.changeTargetDeadline(updateTargetRequest.getTargetIdToChange(), updateTargetRequest.getNewTargetDeadline());

        return new UpdateTargetResponse(updateTargetRequest.getTargetIdToChange(),
                updateTargetRequest.getNewTargetName(),
                updateTargetRequest.getNewTargetDescription(),
                updateTargetRequest.getNewTargetDeadline());
    }
}
