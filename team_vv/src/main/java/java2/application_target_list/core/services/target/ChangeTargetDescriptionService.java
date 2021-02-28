package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.target.ChangeTargetDescriptionRequest;
import java2.application_target_list.core.responses.target.ChangeTargetDescriptionResponse;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.validators.target.ChangeTargetDescriptionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ChangeTargetDescriptionService {

    @Autowired
    private JpaTargetRepository jpaTargetRepository;
    @Autowired
    private ChangeTargetDescriptionValidator validator;

    private List<CoreError> errors;

    public ChangeTargetDescriptionResponse execute(ChangeTargetDescriptionRequest changeTargetDescriptionRequest){

        errors = checkRequestFoErrors(changeTargetDescriptionRequest);
        checkAvailabilityInDB(changeTargetDescriptionRequest);

        if (requestHaveErrors()) {
            return createChangeTargetDescriptionResponseWithErrors();
        }

        changeTargetDescription(changeTargetDescriptionRequest);
        return createChangeTargetDescriptionResponse(changeTargetDescriptionRequest);
    }

    private void changeTargetDescription(ChangeTargetDescriptionRequest changeTargetDescriptionRequest){
        jpaTargetRepository.changeTargetDescription(changeTargetDescriptionRequest.getTargetIdToChange(), changeTargetDescriptionRequest.getNewTargetDescription());
    }

    private boolean requestHaveErrors() {
        return !errors.isEmpty();
    }

    private ChangeTargetDescriptionResponse createChangeTargetDescriptionResponse(ChangeTargetDescriptionRequest changeTargetDescriptionRequest){
        return new ChangeTargetDescriptionResponse(changeTargetDescriptionRequest.getTargetIdToChange(), changeTargetDescriptionRequest.getNewTargetDescription());
    }

    private ChangeTargetDescriptionResponse createChangeTargetDescriptionResponseWithErrors() {
        return new ChangeTargetDescriptionResponse(errors);
    }

    private void checkAvailabilityInDB(ChangeTargetDescriptionRequest changeTargetDescriptionRequest) {
        if (targetDoesNotExist(changeTargetDescriptionRequest)) {
            errors.add(createTargetDoesNotExistError());
        }
    }

    private boolean targetDoesNotExist(ChangeTargetDescriptionRequest changeTargetDescriptionRequest){
        return !jpaTargetRepository.existsById(changeTargetDescriptionRequest.getTargetIdToChange());
    }

    private CoreError createTargetDoesNotExistError(){
        return new CoreError("Target ID;", "no target with that ID");
    }

    private List<CoreError> checkRequestFoErrors(ChangeTargetDescriptionRequest changeTargetDescriptionRequest) {
        return validator.validate(changeTargetDescriptionRequest);
    }
}
