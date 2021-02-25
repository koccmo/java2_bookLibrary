package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.user.DeleteUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.DeleteUserResponse;
import java2.application_target_list.core.validators.user.DeleteUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@Component
@Service
@Transactional
public class DeleteUserService {

    @Autowired private DeleteUserValidator deleteUserValidator;
    @Autowired private JpaUserRepository jpaUserRepository;

    public DeleteUserResponse execute(DeleteUserRequest request){
        List<CoreError> errors = deleteUserValidator.validate(request);

        if (!jpaUserRepository.existsById(request.getUserIdToDelete())){
            errors.add(new CoreError("User ID;","no user with that ID"));
        }

        if(!errors.isEmpty()){
            return new DeleteUserResponse(errors);
        }

        jpaUserRepository.deleteById(request.getUserIdToDelete());
        return new DeleteUserResponse(request.getUserIdToDelete());

    }
}
