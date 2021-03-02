package java2.application_target_list.core.validators.user;


import java2.application_target_list.core.requests.user.UpdateUserRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateUserValidator {

    private List<CoreError> errors;

    public List<CoreError> validate(UpdateUserRequest updateUserRequest) {
        errors = new ArrayList<>();
        checkUserId(updateUserRequest);
        checkUserFirstName(updateUserRequest);
        checkUserLastName(updateUserRequest);
        return errors;
    }

    private void checkUserId(UpdateUserRequest updateUserRequest){
        if (isUserIdEmpty(updateUserRequest)){
            errors.add(createUserIdIsEmptyError());
        }
        if (isUserIdNegative(updateUserRequest)){
            errors.add(createUserIdIsNegativeError());
        }
    }

    private void checkUserFirstName(UpdateUserRequest updateUserRequest){
        if (isUserLastNameEmpty(updateUserRequest)){
            errors.add(createUserFirstNameIsEmpty());
        }
    }

    private void checkUserLastName(UpdateUserRequest updateUserRequest){
        if (isUserFirstNameEmpty(updateUserRequest)){
            errors.add(createUserLastNameIsEmptyError());
        }
    }

    private CoreError createUserLastNameIsEmptyError(){
        return new CoreError("User new first name","must not be empty!");
    }

    private CoreError createUserIdIsNegativeError(){
        return new CoreError("User ID","must not be negative!");
    }

    private CoreError createUserIdIsEmptyError(){
        return new CoreError("User ID","must not be empty!");
    }

    private CoreError createUserFirstNameIsEmpty(){
        return new CoreError("User new last name","must not be empty!");
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
