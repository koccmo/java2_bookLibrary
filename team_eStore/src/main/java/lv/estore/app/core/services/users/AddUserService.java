package lv.estore.app.core.services.users;

import lv.estore.app.core.database.users.UserRepository;
import lv.estore.app.core.domain.User;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.users.AddUserRequest;
import lv.estore.app.core.responses.users.AddUserResponse;
import lv.estore.app.core.validators.users.AddUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static lv.estore.app.utils.CommonUtils.transformDate;

@Component
public class AddUserService {

    @Autowired
    AddUserValidator validator;

    @Autowired
    UserRepository database;


    public AddUserResponse execute(final AddUserRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddUserResponse(errors);
        } else {
            User user = new User(request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    transformDate(LocalDateTime.now()));
            Long id = database.addUser(user);
            return new AddUserResponse(id != 0);
        }
    }
}
