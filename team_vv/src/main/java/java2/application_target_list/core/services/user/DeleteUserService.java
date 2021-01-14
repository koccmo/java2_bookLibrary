package java2.application_target_list.core.services.user;


import java2.application_target_list.core.database.UserDatabase;
import java2.application_target_list.core.requests.user.DeleteUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.DeleteUserResponse;
import java2.application_target_list.core.validators.user.DeleteUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteUserService {

    @Autowired private UserDatabase userDatabase;
    @Autowired private DeleteUserValidator deleteuserValidator;

    public DeleteUserResponse execute(DeleteUserRequest request){
        List<CoreError> errors = deleteuserValidator.validate(request, userDatabase);

        if(!errors.isEmpty()){
            return new DeleteUserResponse(errors);
        }

        userDatabase.deleteUser(request.getUserIdToDelete());
        return new DeleteUserResponse(request.getUserIdToDelete());

    }
}
