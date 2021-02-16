package lv.estore.app.core.validators.users;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.users.UserEmailRequest;
import lv.estore.app.core.validators.common_validation_rules.ValidationRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserEmailValidator {

    @Autowired
    ValidationRules validationRules;

    public List<CoreError> validate(final UserEmailRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validationRules.validateTextField("email", request.getEmail()).ifPresent(errors::add);
        return errors;
    }
}
