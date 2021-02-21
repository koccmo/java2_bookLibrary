package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.user.UserRepository;
import java2.application_target_list.core.requests.user.UpdateUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.UpdateUserResponse;
import java2.application_target_list.core.validators.user.UpdateUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateUserService {

    @Autowired private UpdateUserValidator updateUserValidator;
    @Autowired private UserRepository userRepository;

    public UpdateUserResponse execute(UpdateUserRequest updateUserRequest){
        List<CoreError> errors = updateUserValidator.validate(updateUserRequest, userRepository);

        if (!errors.isEmpty()){
            return new UpdateUserResponse(errors);
        }

        userRepository.changeUserFirstName(updateUserRequest.getUserIdToChange(), updateUserRequest.getNewUserFirstName());
        userRepository.changeUserLastName(updateUserRequest.getUserIdToChange(), updateUserRequest.getNewUserLastName());

        return new UpdateUserResponse(updateUserRequest.getUserIdToChange(),
                updateUserRequest.getNewUserFirstName(),
                updateUserRequest.getNewUserLastName());

    }
}
