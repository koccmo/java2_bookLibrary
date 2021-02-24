package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.user.UpdateUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.UpdateUserResponse;
import java2.application_target_list.core.validators.user.UpdateUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@Component
@Service
@Transactional
public class UpdateUserService {

    @Autowired private UpdateUserValidator updateUserValidator;
    @Autowired private JpaUserRepository jpaUserRepository;

    public UpdateUserResponse execute(UpdateUserRequest updateUserRequest){
        List<CoreError> errors = updateUserValidator.validate(updateUserRequest);

        if (!jpaUserRepository.existsById(updateUserRequest.getUserIdToChange())){
            errors.add(new CoreError("User ID;","no user with that ID"));
        }

        if (!errors.isEmpty()){
            return new UpdateUserResponse(errors);
        }

        jpaUserRepository.changeUserFirstName(updateUserRequest.getUserIdToChange(), updateUserRequest.getNewUserFirstName());
        jpaUserRepository.changeUserLastName(updateUserRequest.getUserIdToChange(), updateUserRequest.getNewUserLastName());

        return new UpdateUserResponse(updateUserRequest.getUserIdToChange(),
                updateUserRequest.getNewUserFirstName(),
                updateUserRequest.getNewUserLastName());
    }
}
