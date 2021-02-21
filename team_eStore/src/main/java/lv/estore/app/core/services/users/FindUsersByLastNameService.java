package lv.estore.app.core.services.users;

import lv.estore.app.core.database.users.UserRepository;
import lv.estore.app.core.domain.User;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.users.UserNameRequest;
import lv.estore.app.core.responses.users.GetUsersResponse;
import lv.estore.app.core.validators.users.UserNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindUsersByLastNameService {

    @Autowired
    UserRepository database;

    @Autowired
    UserNameValidator validator;

    public GetUsersResponse execute(final UserNameRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetUsersResponse(errors, null);
        }
        List<User> users = database.findUsersByLastName(request.getName());
        return new GetUsersResponse(null, users);
    }
}
