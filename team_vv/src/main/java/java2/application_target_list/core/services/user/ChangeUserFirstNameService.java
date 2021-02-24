package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.user.ChangeUserFirstNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.ChangeUserFirstNameResponse;
import java2.application_target_list.core.validators.user.ChangeUserFirstNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@Component
@Service
@Transactional
public class ChangeUserFirstNameService {

    @Autowired private ChangeUserFirstNameValidator changeUserFirstNameValidator;
    @Autowired private JpaUserRepository jpaUserRepository;

    public ChangeUserFirstNameResponse execute(ChangeUserFirstNameRequest request){
        List<CoreError> errors = changeUserFirstNameValidator.validate(request);

        if (!jpaUserRepository.existsById(request.getUserIdToChange())){
            errors.add(new CoreError("User ID;","no user with that ID"));
        }

        if (!errors.isEmpty()){
            return new ChangeUserFirstNameResponse(errors);
        }

        jpaUserRepository.changeUserFirstName(request.getUserIdToChange(), request.getNewUserFirstName());
        return new ChangeUserFirstNameResponse(request.getUserIdToChange(), request.getNewUserFirstName());
    }
}
