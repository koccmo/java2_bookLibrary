package java2.application_target_list.core.validators.user;


import java2.application_target_list.core.database.user.UserDatabase;
import java2.application_target_list.core.requests.user.DeleteUserRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteUserValidator {

    public List<CoreError> validate(DeleteUserRequest request, UserDatabase userDatabase) {
        List<CoreError> errors = new ArrayList<>();


        if (!userDatabase.isIdInUserList(request.getUserIdToDelete())){
            errors.add(new CoreError("User ID;","no user with that ID"));
        }

        if (isUserIdEmpty(request)){
            errors.add(new CoreError("User ID","must not be empty!"));
        }
        if (isUserIdNegative(request)){
            errors.add(new CoreError("User ID","must not be negative!"));
        }

        return errors;
    }

    private boolean isUserIdEmpty(DeleteUserRequest request) {
        return request.getUserIdToDelete() == null;
    }

    private boolean isUserIdNegative(DeleteUserRequest request){
        return request.getUserIdToDelete() < 0;
    }
}
