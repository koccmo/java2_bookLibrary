package java2.application_target_list.core.services.user;

import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.user.GetAllUsersRequest;
import java2.application_target_list.core.responses.user.GetAllUsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

//@Component
@Service
@Transactional
public class GetAllUserService {

    @Autowired private JpaUserRepository jpaUserRepository;

    public GetAllUsersResponse execute(GetAllUsersRequest request){
        return new GetAllUsersResponse(jpaUserRepository.findAll());
    }
}
