package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.user.DeleteUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.DeleteUserResponse;
import java2.application_target_list.core.validators.user.DeleteUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DeleteUserService {

    @Autowired
    private DeleteUserValidator deleteUserValidator;
    @Autowired
    private JpaUserRepository jpaUserRepository;

    private List<CoreError> errors;

    public DeleteUserResponse execute(DeleteUserRequest deleteUserRequest){
        errors = checkRequestForErrors(deleteUserRequest);
        checkAvailabilityInDB(deleteUserRequest);


        if(requestHaveErrors()){
            return createDeleteUserResponseWithErrors();
        }

        deleteUserFromDB(deleteUserRequest);
        return createDeleteUserResponse(deleteUserRequest);
    }

    private DeleteUserResponse createDeleteUserResponse(DeleteUserRequest deleteUserRequest){
        return new DeleteUserResponse(deleteUserRequest.getUserIdToDelete());
    }

    private void deleteUserFromDB(DeleteUserRequest deleteUserRequest){
        jpaUserRepository.deleteById(deleteUserRequest.getUserIdToDelete());
    }

    private DeleteUserResponse createDeleteUserResponseWithErrors(){
        return new DeleteUserResponse(errors);
    }

    private boolean requestHaveErrors(){
        return !errors.isEmpty();
    }

    private boolean userDoesNotExist(DeleteUserRequest deleteUserRequest) {
        return !jpaUserRepository.existsById(deleteUserRequest.getUserIdToDelete());
    }

    private CoreError createUserDoesNotExistError(){
        return new CoreError("User ID;","no user with that ID");
    }

    private void checkAvailabilityInDB(DeleteUserRequest deleteUserRequest){
        if (userDoesNotExist(deleteUserRequest)){
            errors.add(createUserDoesNotExistError());
        }
    }

    private List<CoreError> checkRequestForErrors(DeleteUserRequest deleteUserRequest){
        return deleteUserValidator.validate(deleteUserRequest);
    }
}
