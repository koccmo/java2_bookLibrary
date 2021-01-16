package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.UserDatabase;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllUserService {

    @Autowired private UserDatabase userDatabase;

    public GetAllUsersResponse execute(GetAllUsersRequest request){
        return new GetAllUsersResponse(userDatabase.getUsersList());
    }
}
