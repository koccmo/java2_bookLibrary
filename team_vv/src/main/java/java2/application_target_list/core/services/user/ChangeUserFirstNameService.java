package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.user.ChangeUserFirstNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.ChangeUserFirstNameResponse;
import java2.application_target_list.core.validators.user.ChangeUserFirstNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ChangeUserFirstNameService {

    @Autowired
    private ChangeUserFirstNameValidator changeUserFirstNameValidator;
    @Autowired
    private JpaUserRepository jpaUserRepository;

    private List<CoreError> errors;

    public ChangeUserFirstNameResponse execute(ChangeUserFirstNameRequest changeUserFirstNameRequest){
        errors = checkRequestForErrors(changeUserFirstNameRequest);
        checkAvailabilityInDB(changeUserFirstNameRequest);

        if (requestHaveErrors()){
            return createChangeUserFirstNameResponseWithErrors();
        }

        changeUserFirstName(changeUserFirstNameRequest);
        return createChangeUserFirstNameResponse(changeUserFirstNameRequest);
    }

    private ChangeUserFirstNameResponse createChangeUserFirstNameResponse(ChangeUserFirstNameRequest changeUserFirstNameRequest){
        return new ChangeUserFirstNameResponse(changeUserFirstNameRequest.getUserIdToChange(), changeUserFirstNameRequest.getNewUserFirstName());
    }

    private void changeUserFirstName(ChangeUserFirstNameRequest changeUserFirstNameRequest) {
        jpaUserRepository.changeUserFirstName(changeUserFirstNameRequest.getUserIdToChange(), changeUserFirstNameRequest.getNewUserFirstName());
    }

    private ChangeUserFirstNameResponse createChangeUserFirstNameResponseWithErrors() {
        return new ChangeUserFirstNameResponse(errors);
    }

    private boolean requestHaveErrors() {
        return !errors.isEmpty();
    }

    private boolean userDoesNotExists(ChangeUserFirstNameRequest changeUserFirstNameRequest){
        return !jpaUserRepository.existsById(changeUserFirstNameRequest.getUserIdToChange());
    }

    private CoreError createUserDoesNotExistError(){
        return new CoreError("User ID;","no user with that ID");
    }

    private void checkAvailabilityInDB(ChangeUserFirstNameRequest changeUserFirstNameRequest){
        if (userDoesNotExists(changeUserFirstNameRequest)){
            errors.add(createUserDoesNotExistError());
        }
    }

    private List<CoreError> checkRequestForErrors(ChangeUserFirstNameRequest changeUserFirstNameRequest) {
        return changeUserFirstNameValidator.validate(changeUserFirstNameRequest);
    }
}
