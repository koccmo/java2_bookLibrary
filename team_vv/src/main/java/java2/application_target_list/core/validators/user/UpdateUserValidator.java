package java2.application_target_list.core.validators.user;

import java2.application_target_list.core.database.user.UserRepository;
import java2.application_target_list.core.requests.user.ChangeUserFirstNameRequest;
import java2.application_target_list.core.requests.user.ChangeUserLastNameRequest;
import java2.application_target_list.core.requests.user.UpdateUserRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateUserValidator {

    public List<CoreError> validate(UpdateUserRequest updateUserRequest,
                                    UserRepository userRepository) {
        List<CoreError> errors = new ArrayList<>();

        if (!userRepository.isIdInUserList(updateUserRequest.getUserIdToChange())){
            errors.add(new CoreError("User ID;","no user with that ID"));
        }

        if (isUserIdEmpty(updateUserRequest)){
            errors.add(new CoreError("User ID","must not be empty!"));
        }
        if (isUserIdNegative(updateUserRequest)){
            errors.add(new CoreError("User ID","must not be negative!"));
        }

        if (isUserLastNameEmpty(updateUserRequest)){
            errors.add(new CoreError("User new last name","must not be empty!"));
        }

        if (isUserFirstNameEmpty(updateUserRequest)){
            errors.add(new CoreError("User new first name","must not be empty!"));
        }

        return errors;

    }

    private boolean isUserFirstNameEmpty(UpdateUserRequest request) {
        return request.getNewUserFirstName() == null || request.getNewUserFirstName().isEmpty();
    }

    private boolean isUserLastNameEmpty(UpdateUserRequest request) {
        return request.getNewUserLastName() == null || request.getNewUserLastName().isEmpty();
    }

    private boolean isUserIdEmpty(UpdateUserRequest request) {
        return request.getUserIdToChange() == null;
    }

    private boolean isUserIdNegative(UpdateUserRequest request){
        return request.getUserIdToChange() < 0;
    }

}
