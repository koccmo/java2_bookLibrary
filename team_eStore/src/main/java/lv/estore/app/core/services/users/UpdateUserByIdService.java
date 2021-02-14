package lv.estore.app.core.services.users;

import lv.estore.app.core.database.users.UserRepository;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.users.UserUpdateRequest;
import lv.estore.app.core.responses.users.UpdateUserResponse;
import lv.estore.app.core.validators.users.UserUpdateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static lv.estore.app.utils.CommonUtils.getLong;

@Component
public class UpdateUserByIdService {

    @Autowired
    UserRepository database;

    @Autowired
    UserUpdateValidator validator;

    public UpdateUserResponse execute(final UserUpdateRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateUserResponse(errors);
        }
        boolean isUpdated = database.updateUserById(getLong(request.getId()),
                request.getFirstName(),
                request.getLastName(),
                request.getEmail());
        return new UpdateUserResponse(isUpdated);
    }
}
