package java2.application_target_list.core.validators.user;


import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddUserValidator {

    public List<CoreError> validate(AddUserRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (isUserFirstNameEmpty(request)){
            errors.add(new CoreError("User first name","must not be empty!"));
        }

        if (isUserLastNameEmpty(request)){
            errors.add(new CoreError("User last name", "must not be empty!"));
        }

        return errors;
    }

    private boolean isUserFirstNameEmpty(AddUserRequest request) {
        return request.getFirstName() == null || request.getFirstName().isEmpty();
    }

    private boolean isUserLastNameEmpty(AddUserRequest request) {
        return request.getLastName() == null || request.getLastName().isEmpty();
    }

}
