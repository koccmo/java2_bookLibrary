package lv.estore.app.core.services.passwords;

import lv.estore.app.core.database.passwords.PasswordsRepository;
import lv.estore.app.core.domain.Password;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.passwords.PasswordRequest;
import lv.estore.app.core.responses.passwords.PasswordResponse;
import lv.estore.app.core.validators.passwords.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static lv.estore.app.utils.CommonUtils.getLong;

@Component
public class AddPasswordService {
    @Autowired
    PasswordValidator validator;

    @Autowired
    PasswordsRepository database;

    public PasswordResponse execute(final PasswordRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new PasswordResponse(errors);
        } else {
            Password password = new Password(request.getPassword(), getLong(request.getUserId()));

            Long isAdded = database.addPassword(password);
            return new PasswordResponse(isAdded != 0);
        }
    }
}
