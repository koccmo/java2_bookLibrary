package java2.application_target_list.core.validators.user;

import java2.application_target_list.core.requests.user.ChangeUserFirstNameRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChangeUserFirstNameValidator {

    private List<CoreError> errors;

    public List<CoreError> validate(ChangeUserFirstNameRequest changeUserFirstNameRequest) {
        errors = new ArrayList<>();
        checkUserId(changeUserFirstNameRequest);
        checkUserFirstName(changeUserFirstNameRequest);
        return errors;
    }

    private void checkUserFirstName(ChangeUserFirstNameRequest changeUserFirstNameRequest){
        if (isUserFirstNameEmpty(changeUserFirstNameRequest)){
            errors.add(createUserFirstNameIsEmptyError());
        }
    }

    private void checkUserId(ChangeUserFirstNameRequest changeUserFirstNameRequest){
        if (isUserIdEmpty(changeUserFirstNameRequest)){
            errors.add(createUserIdIsEmptyError());
        }
        if (isUserIdNegative(changeUserFirstNameRequest)){
            errors.add(createUserIdIsNegativeError());
        }
    }

    private CoreError createUserFirstNameIsEmptyError(){
        return new CoreError("User new first name","must not be empty!");
    }

    private CoreError createUserIdIsNegativeError(){
        return new CoreError("User ID","must not be negative!");
    }

    private CoreError createUserIdIsEmptyError(){
        return new CoreError("User ID","must not be empty!");
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
