package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.user.GetUserRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.user.GetUserResponse;
import java2.application_target_list.core.validators.user.GetUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//@Component
@Service
@Transactional
public class GetUserService {

    @Autowired private GetUserValidator getUserValidator;
    @Autowired private JpaUserRepository jpaUserRepository;

    public GetUserResponse execute(GetUserRequest getUserRequest) {
        List<CoreError> errors = getUserValidator.validate(getUserRequest);

        if (!errors.isEmpty()) {
            return new GetUserResponse(errors);
        }

        return jpaUserRepository.findById(getUserRequest.getId())
                .map(GetUserResponse::new).orElseGet(() -> {
                    errors.add(new CoreError("id", "Not found!"));
                    return new GetUserResponse(errors);
                });
    }
}


