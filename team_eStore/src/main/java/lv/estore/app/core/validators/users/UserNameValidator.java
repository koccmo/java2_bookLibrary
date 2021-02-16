package lv.estore.app.core.validators.users;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.users.UserNameRequest;
import lv.estore.app.core.validators.common_validation_rules.ValidationRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserNameValidator {

    @Autowired
    ValidationRules validationRules;

    public List<CoreError> validate(final UserNameRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validationRules.validateTextField("userName", request.getName()).ifPresent(errors::add);
        return errors;
    }
}
