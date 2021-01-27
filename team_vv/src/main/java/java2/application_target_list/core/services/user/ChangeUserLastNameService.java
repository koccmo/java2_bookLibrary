package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.user.UserRepository;
import java2.application_target_list.core.requests.user.ChangeUserLastNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.ChangeUserLastNameResponse;
import java2.application_target_list.core.validators.user.ChangeUserLastNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChangeUserLastNameService {

    @Autowired private UserRepository userRepository;
    @Autowired private ChangeUserLastNameValidator changeUserLastNameValidator;

    public ChangeUserLastNameResponse execute(ChangeUserLastNameRequest request){
        List<CoreError> errors = changeUserLastNameValidator.validate(request, userRepository);

        if (!errors.isEmpty()){
            return new ChangeUserLastNameResponse(errors);
        }

        userRepository.changeUserLastName(request.getUserIdToChange(), request.getNewUserLastName());
        return new ChangeUserLastNameResponse(request.getUserIdToChange(), request.getNewUserLastName());

    }
}
