package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.user.ChangeUserLastNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.ChangeUserLastNameResponse;
import java2.application_target_list.core.validators.user.ChangeUserLastNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ChangeUserLastNameService {

    @Autowired
    private ChangeUserLastNameValidator changeUserLastNameValidator;
    @Autowired
    private JpaUserRepository jpaUserRepository;

    private List<CoreError> errors;

    public ChangeUserLastNameResponse execute(ChangeUserLastNameRequest changeUserLastNameRequest){
        errors = checkRequestForErrors(changeUserLastNameRequest);
        checkAvailabilityInDB(changeUserLastNameRequest);

        if (requestHaveErrors()){
            return createChangeUserLastNameResponseWithErrors();
        }

        changeUserLastName(changeUserLastNameRequest);
        return createChangeUserLastNameResponse(changeUserLastNameRequest);
    }

    private ChangeUserLastNameResponse createChangeUserLastNameResponse(ChangeUserLastNameRequest changeUserLastNameRequest){
        return new ChangeUserLastNameResponse(changeUserLastNameRequest.getUserIdToChange(), changeUserLastNameRequest.getNewUserLastName());
    }

    private void changeUserLastName(ChangeUserLastNameRequest changeUserLastNameRequest){
        jpaUserRepository.changeUserLastName(changeUserLastNameRequest.getUserIdToChange(), changeUserLastNameRequest.getNewUserLastName());
    }

    private ChangeUserLastNameResponse createChangeUserLastNameResponseWithErrors(){
        return new ChangeUserLastNameResponse(errors);
    }

    private boolean requestHaveErrors() {
        return !errors.isEmpty();
    }

    private boolean userDoesNotExist(ChangeUserLastNameRequest changeUserLastNameRequest){
        return !jpaUserRepository.existsById(changeUserLastNameRequest.getUserIdToChange());
    }

    private CoreError createUserDoesNotExistError(){
        return new CoreError("User ID;","no user with that ID");
    }

    private void checkAvailabilityInDB(ChangeUserLastNameRequest changeUserLastNameRequest){
        if (userDoesNotExist(changeUserLastNameRequest)){
            errors.add(createUserDoesNotExistError());
        }
    }

    private List<CoreError> checkRequestForErrors(ChangeUserLastNameRequest changeUserLastNameRequest) {
        return changeUserLastNameValidator.validate(changeUserLastNameRequest);
    }
}
