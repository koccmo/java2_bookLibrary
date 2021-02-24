package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.user.ChangeUserLastNameRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.ChangeUserLastNameResponse;
import java2.application_target_list.core.validators.user.ChangeUserLastNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@Component
@Service
@Transactional
public class ChangeUserLastNameService {

    @Autowired private ChangeUserLastNameValidator changeUserLastNameValidator;
    @Autowired private JpaUserRepository jpaUserRepository;

    public ChangeUserLastNameResponse execute(ChangeUserLastNameRequest request){
        List<CoreError> errors = changeUserLastNameValidator.validate(request);

        if (!jpaUserRepository.existsById(request.getUserIdToChange())){
            errors.add(new CoreError("User ID;","no user with that ID"));
        }

        if (!errors.isEmpty()){
            return new ChangeUserLastNameResponse(errors);
        }

        jpaUserRepository.changeUserLastName(request.getUserIdToChange(), request.getNewUserLastName());
        return new ChangeUserLastNameResponse(request.getUserIdToChange(), request.getNewUserLastName());

    }
}
