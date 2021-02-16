package lv.estore.app.core.services.users;

import lv.estore.app.core.database.users.UserRepository;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.users.UserIdRequest;
import lv.estore.app.core.responses.users.FindUserByIdResponse;
import lv.estore.app.core.validators.users.UserIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static lv.estore.app.utils.CommonUtils.getLong;

@Component
public class FindUserByIdService {

    @Autowired
    UserIdValidator validator;

    @Autowired
    UserRepository database;

    public FindUserByIdResponse execute(final UserIdRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindUserByIdResponse(errors);
        }
        return new FindUserByIdResponse(database.findUserById(getLong(request.getId())));
    }
}
