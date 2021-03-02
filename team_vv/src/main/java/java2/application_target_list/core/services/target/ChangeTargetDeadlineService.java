package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java2.application_target_list.core.requests.target.ChangeTargetDeadlineRequest;
import java2.application_target_list.core.responses.target.ChangeTargetDeadlineResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.target.ChangeTargetDeadlineValidator;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ChangeTargetDeadlineService {

    @Autowired
    private JpaTargetRepository jpaTargetRepository;
    @Autowired
    private ChangeTargetDeadlineValidator validator;

    private List<CoreError> errors;

    public ChangeTargetDeadlineResponse execute(ChangeTargetDeadlineRequest changeTargetDeadlineRequest){

        errors = checkRequestForErrors(changeTargetDeadlineRequest);
        checkAvailabilityInDB(changeTargetDeadlineRequest);

        if (requestHaveErrors()) {
            return new ChangeTargetDeadlineResponse(errors);
        }

        changeTargetDeadline(changeTargetDeadlineRequest);
        return createChangeTargetDeadlineResponse(changeTargetDeadlineRequest);
    }

    private ChangeTargetDeadlineResponse createChangeTargetDeadlineResponse(ChangeTargetDeadlineRequest changeTargetDeadlineRequest) {
        return new ChangeTargetDeadlineResponse(changeTargetDeadlineRequest.getTargetIdToChange(), changeTargetDeadlineRequest.getNewTargetDeadline());
    }

    private void changeTargetDeadline(ChangeTargetDeadlineRequest changeTargetDeadlineRequest){
        jpaTargetRepository.changeTargetDeadline(changeTargetDeadlineRequest.getTargetIdToChange(), changeTargetDeadlineRequest.getNewTargetDeadline());
    }

    private boolean targetDoesNotExistInDB(ChangeTargetDeadlineRequest changeTargetDeadlineRequest){
        return !jpaTargetRepository.existsById(changeTargetDeadlineRequest.getTargetIdToChange());
    }

    private void checkAvailabilityInDB(ChangeTargetDeadlineRequest changeTargetDeadlineRequest){
        if (targetDoesNotExistInDB(changeTargetDeadlineRequest)){
            errors.add(createTargetDoesNotExistError());
        }
    }

    private CoreError createTargetDoesNotExistError() {
        return new CoreError("Target ID;","no target with that ID");
    }

    private boolean requestHaveErrors() {
        return !errors.isEmpty();
    }

    private List<CoreError> checkRequestForErrors(ChangeTargetDeadlineRequest changeTargetDeadlineRequest) {
        return validator.validate(changeTargetDeadlineRequest);
    }
}
