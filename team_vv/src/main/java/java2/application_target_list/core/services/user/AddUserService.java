package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.AddUserResponse;
import java2.application_target_list.core.validators.user.AddUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@Component
@Service
@Transactional
public class AddUserService {

    @Autowired private AddUserValidator addUserValidator;
    @Autowired private JpaUserRepository jpaUserRepository;

    public AddUserResponse execute(AddUserRequest request){
        List<CoreError> errors = addUserValidator.validate(request);

        if (!errors.isEmpty()){
            return new AddUserResponse(errors);
        }

        User user = new User(request.getFirstName(), request.getLastName());
        jpaUserRepository.save(user);
        return new AddUserResponse(user);

    }

}
