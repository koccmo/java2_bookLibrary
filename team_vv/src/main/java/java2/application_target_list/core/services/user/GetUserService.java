package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.user.UserRepository;
import java2.application_target_list.core.requests.user.GetUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.GetUserResponse;
import java2.application_target_list.core.validators.user.GetUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class GetUserService {

    @Autowired private GetUserValidator getUserValidator;
    @Autowired private UserRepository userRepository;

    public GetUserResponse execute(GetUserRequest getUserRequest){
        List<CoreError> errors = getUserValidator.validate(getUserRequest);

        if (!errors.isEmpty()){
            return new GetUserResponse(errors);
        }

        return userRepository.getById(getUserRequest.getId()).map(GetUserResponse::new).orElseGet(() ->{
            errors.add(new CoreError("id", "Not found!"));
            return new GetUserResponse(errors);});
        }
    }


