package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.user.GetUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.GetUserResponse;
import java2.application_target_list.core.validators.user.GetUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GetUserService {

    @Autowired
    private GetUserValidator getUserValidator;
    @Autowired
    private JpaUserRepository jpaUserRepository;

    private List<CoreError> errors;

    public GetUserResponse execute(GetUserRequest getUserRequest) {
         errors = checkRequestForErrors(getUserRequest);

        if (requestHaveErrors()) {
            return createGetUserResponseWithErrors();
        }

        return createGetUserResponse(getUserRequest);
    }

    private GetUserResponse createGetUserResponse(GetUserRequest getUserRequest){
        return jpaUserRepository.findById(getUserRequest.getId())
                .map(GetUserResponse::new).orElseGet(() -> {
                    errors.add(createUserDoesNotExistError());
                    return createGetUserResponseWithErrors();
                });
    }

    private GetUserResponse createGetUserResponseWithErrors() {
        return new GetUserResponse(errors);
    }

    private boolean requestHaveErrors(){
        return !errors.isEmpty();
    }

    private CoreError createUserDoesNotExistError(){
        return new CoreError("id", "Not found!");
    }

    private List<CoreError> checkRequestForErrors(GetUserRequest getUserRequest){
        return getUserValidator.validate(getUserRequest);
    }
}


