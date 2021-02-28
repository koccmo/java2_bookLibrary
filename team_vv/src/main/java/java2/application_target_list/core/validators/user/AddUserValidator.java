package java2.application_target_list.core.validators.user;


import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class AddUserValidator {

    private List<CoreError> errors;

    public List<CoreError> validate(AddUserRequest addUserRequest) {
        errors = new ArrayList<>();
        checkUserFirstName(addUserRequest);
        checkUserLastName(addUserRequest);
        return errors;
    }

    private void  checkUserLastName(AddUserRequest addUserRequest){
        if (isUserLastNameEmpty(addUserRequest)){
            errors.add(createUserLastNameIsEmptyError());
        }
    }

    private void checkUserFirstName(AddUserRequest addUserRequest){
        if (isUserFirstNameEmpty(addUserRequest)){
            errors.add(createUserFirstNameIsEmptyError());
        }
    }

    private CoreError createUserFirstNameIsEmptyError(){
        return new CoreError("User first name","must not be empty!");
    }

    private CoreError createUserLastNameIsEmptyError(){
        return new CoreError("User last name", "must not be empty!");
    }

    private boolean isUserFirstNameEmpty(AddUserRequest request) {
        return request.getFirstName() == null || request.getFirstName().isEmpty();
    }

    private boolean isUserLastNameEmpty(AddUserRequest request) {
        return request.getLastName() == null || request.getLastName().isEmpty();
    }

}
