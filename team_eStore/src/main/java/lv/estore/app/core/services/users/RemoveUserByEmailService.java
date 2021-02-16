package lv.estore.app.core.services.users;

import lv.estore.app.core.database.users.UserRepository;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.users.UserEmailRequest;
import lv.estore.app.core.responses.users.RemoveUserResponse;
import lv.estore.app.core.validators.users.UserEmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveUserByEmailService {

    @Autowired
    UserRepository database;

    @Autowired
    UserEmailValidator validator;

    public RemoveUserResponse execute(final UserEmailRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
             return new RemoveUserResponse(errors);
        }
        boolean isRemoved = database.removeUserByEmail(request.getEmail());
        return new RemoveUserResponse(isRemoved);
    }
}
