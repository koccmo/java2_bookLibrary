package java2.application_target_list.core.validators.user;


import java2.application_target_list.core.requests.user.DeleteUserRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteUserValidator {

    private List<CoreError> errors;

    public List<CoreError> validate(DeleteUserRequest deleteUserRequest) {
        errors = new ArrayList<>();
        checkUserId(deleteUserRequest);
        return errors;
    }

    private void checkUserId(DeleteUserRequest deleteUserRequest){
        if (isUserIdEmpty(deleteUserRequest)){
            errors.add(createUserIdIsEmptyError());
        }
        if (isUserIdNegative(deleteUserRequest)){
            errors.add(createUserIdIsNegativeError());
        }
    }

    private CoreError createUserIdIsNegativeError(){
        return new CoreError("User ID","must not be negative!");
    }

    private CoreError createUserIdIsEmptyError(){
        return new CoreError("User ID","must not be empty!");
    }

    private boolean isUserIdEmpty(DeleteUserRequest request) {
        return request.getUserIdToDelete() == null;
    }

    private boolean isUserIdNegative(DeleteUserRequest request){
        return request.getUserIdToDelete() < 0;
    }
}
