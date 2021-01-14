package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.UserDatabase;
import java2.application_target_list.core.requests.user.ChangeUserFirstNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.ChangeUserFirstNameResponse;
import java2.application_target_list.core.validators.user.ChangeUserFirstNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChangeUserFirstNameService {

    @Autowired private UserDatabase userDatabase;
    @Autowired private ChangeUserFirstNameValidator changeUserFirstNameValidator;

    public ChangeUserFirstNameResponse execute(ChangeUserFirstNameRequest request){
        List<CoreError> errors = changeUserFirstNameValidator.validate(request, userDatabase);

        if (!errors.isEmpty()){
            return new ChangeUserFirstNameResponse(errors);
        }

        userDatabase.changeUserFirstName(request.getUserIdToChange(), request.getNewUserFirstName());
        return new ChangeUserFirstNameResponse(request.getUserIdToChange(), request.getNewUserFirstName());
    }
}
