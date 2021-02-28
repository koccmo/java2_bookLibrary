package java2.application_target_list.core.services.target;

import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.requests.target.ChangeTargetNameRequest;
import java2.application_target_list.core.validators.target.ChangeTargetNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import java2.application_target_list.core.responses.target.ChangeTargetNameResponse;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ChangeTargetNameService {

    @Autowired
    private JpaTargetRepository jpaTargetRepository;
    @Autowired
    private ChangeTargetNameValidator validator;

    private List<CoreError> errors;

    public ChangeTargetNameResponse execute(ChangeTargetNameRequest changeTargetNameRequest){

        errors = validator.validate(changeTargetNameRequest);
        checkAvailabilityInDB(changeTargetNameRequest);

        if (requestHavErrors()) {
            return createChangeTargetNameResponseWithErrors();
        }

        changeTargetName(changeTargetNameRequest);
        return createChangeTargetNameResponse(changeTargetNameRequest);
    }

    private ChangeTargetNameResponse createChangeTargetNameResponse(ChangeTargetNameRequest changeTargetNameRequest){
        return new ChangeTargetNameResponse(changeTargetNameRequest.getTargetIdToChange(), changeTargetNameRequest.getNewTargetName());
    }

    private void changeTargetName(ChangeTargetNameRequest changeTargetNameRequest){
        jpaTargetRepository.changeTargetName(changeTargetNameRequest.getTargetIdToChange(), changeTargetNameRequest.getNewTargetName());
    }

    private ChangeTargetNameResponse createChangeTargetNameResponseWithErrors() {
        return new ChangeTargetNameResponse(errors);
    }

    private boolean requestHavErrors() {
        return !errors.isEmpty();
    }

    private void checkAvailabilityInDB(ChangeTargetNameRequest changeTargetNameRequest) {
        if (targetDoesNotExist(changeTargetNameRequest)){
            errors.add(createTargetDoesNotExistError());
        }
    }

    private boolean targetDoesNotExist(ChangeTargetNameRequest changeTargetNameRequest) {
        return !jpaTargetRepository.existsById(changeTargetNameRequest.getTargetIdToChange());
    }

    private CoreError createTargetDoesNotExistError() {
        return new CoreError("Target ID;","no target with that ID");
    }

    private List<CoreError> checkRequestForErrors(ChangeTargetNameRequest changeTargetNameRequest) {
        return validator.validate(changeTargetNameRequest);
    }
}
