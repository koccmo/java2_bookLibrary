package java2.application_target_list.core.validators.user;

import java2.application_target_list.core.requests.user.ChangeUserLastNameRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeUserLastNameValidator {


    public List<CoreError> validate(ChangeUserLastNameRequest request) {
        List<CoreError> errors = new ArrayList<>();

        if (isUserIdEmpty(request)){
            errors.add(new CoreError("User ID","must not be empty!"));
        }
        if (isUserIdNegative(request)){
            errors.add(new CoreError("User ID","must not be negative!"));
        }

        if (isUserLastNameEmpty(request)){
            errors.add(new CoreError("User new last name","must not be empty!"));
        }

        return errors;
    }

    private boolean isUserLastNameEmpty(ChangeUserLastNameRequest request) {
        return request.getNewUserLastName() == null || request.getNewUserLastName().isEmpty();
    }

    private boolean isUserIdEmpty(ChangeUserLastNameRequest request) {
        return request.getUserIdToChange() == null;
    }

    private boolean isUserIdNegative(ChangeUserLastNameRequest request){
        return request.getUserIdToChange() < 0;
    }
}
