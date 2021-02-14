package lv.estore.app.core.services.users;

import lv.estore.app.core.database.users.UserRepository;
import lv.estore.app.core.domain.User;
import lv.estore.app.core.request.users.GetAllUsersRequest;
import lv.estore.app.core.responses.users.GetUsersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllUsersService {

    @Autowired
    UserRepository database;

    public GetUsersResponse execute(final GetAllUsersRequest request) {
        List<User> users = database.getAllUsers();
        return new GetUsersResponse(null, users);
    }
}
