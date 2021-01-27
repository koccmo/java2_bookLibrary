package java2.application_target_list.core.validators.user;


import java2.application_target_list.core.database.user.UserRepository;
import java2.application_target_list.core.requests.user.ChangeUserFirstNameRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeUserFirstNameValidator {

    public List<CoreError> validate(ChangeUserFirstNameRequest request, UserRepository userRepository) {
        List<CoreError> errors = new ArrayList<>();

        if (!userRepository.isIdInUserList(request.getUserIdToChange())){
            errors.add(new CoreError("User ID;","no user with that ID"));
        }

        if (isUserIdEmpty(request)){
            errors.add(new CoreError("User ID","must not be empty!"));
        }
        if (isUserIdNegative(request)){
            errors.add(new CoreError("User ID","must not be negative!"));
        }

        if (isUserFirstNameEmpty(request)){
            errors.add(new CoreError("User new first name","must not be empty!"));
        }

        return errors;
    }

    private boolean isUserFirstNameEmpty(ChangeUserFirstNameRequest request) {
        return request.getNewUserFirstName() == null || request.getNewUserFirstName().isEmpty();
    }

    private boolean isUserIdEmpty(ChangeUserFirstNameRequest request) {
        return request.getUserIdToChange() == null;
    }

    private boolean isUserIdNegative(ChangeUserFirstNameRequest request){
        return request.getUserIdToChange() < 0;
    }

}
