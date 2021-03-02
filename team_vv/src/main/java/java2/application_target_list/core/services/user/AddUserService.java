package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.AddUserResponse;
import java2.application_target_list.core.validators.user.AddUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AddUserService {

    @Autowired
    private AddUserValidator addUserValidator;
    @Autowired
    private JpaUserRepository jpaUserRepository;

    private List<CoreError> errors;
    private User user;


    public AddUserResponse execute(AddUserRequest addUserRequest){
        errors = checkRequestForErrors(addUserRequest);

        if (requestHaveErrors()){
            return createAddUserResponseWithErrors();
        }

        user = createUser(addUserRequest);
        addUserToDB();
        return createUserResponse();
    }

    private AddUserResponse createUserResponse(){
        return new AddUserResponse(user);
    }

    private void addUserToDB() {
        jpaUserRepository.save(user);
    }

    private User createUser(AddUserRequest addUserRequest){
        return new User(addUserRequest.getFirstName(), addUserRequest.getLastName());
    }

    private AddUserResponse createAddUserResponseWithErrors() {
        return new AddUserResponse(errors);
    }

    private boolean requestHaveErrors() {
        return !errors.isEmpty();
    }

    private List<CoreError> checkRequestForErrors(AddUserRequest addUserRequest) {
        return addUserValidator.validate(addUserRequest);
    }

}
