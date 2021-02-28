package java2.application_target_list.core.validators.user;

import java2.application_target_list.core.requests.user.ChangeUserLastNameRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeUserLastNameValidator {

    private List<CoreError> errors;

    public List<CoreError> validate(ChangeUserLastNameRequest changeUserLastNameRequest) {
        errors = new ArrayList<>();
        checkUserId(changeUserLastNameRequest);
        checkUserLastName(changeUserLastNameRequest);
        return errors;
    }

    private void checkUserLastName(ChangeUserLastNameRequest changeUserLastNameRequest){
        if (isUserLastNameEmpty(changeUserLastNameRequest)){
            errors.add(createUserLastNameIsEmptyError());
        }
    }

    private void checkUserId(ChangeUserLastNameRequest changeUserLastNameRequest){
        if (isUserIdEmpty(changeUserLastNameRequest)){
            errors.add(createUserIdIsEmptyError());
        }
        if (isUserIdNegative(changeUserLastNameRequest)){
            errors.add(createUserIdIsNegativeError());
        }
    }

    private CoreError createUserLastNameIsEmptyError(){
        return new CoreError("User new last name","must not be empty!");
    }

    private CoreError createUserIdIsNegativeError(){
        return new CoreError("User ID","must not be negative!");
    }

    private CoreError createUserIdIsEmptyError(){
        return new CoreError("User ID","must not be empty!");
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
