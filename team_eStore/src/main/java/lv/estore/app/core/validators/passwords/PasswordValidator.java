package lv.estore.app.core.validators.passwords;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.passwords.PasswordRequest;
import lv.estore.app.core.validators.common_validation_rules.ValidationRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PasswordValidator {

    @Autowired
    ValidationRules validationRules;

    public List<CoreError> validate(final PasswordRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validationRules.validateTextField("password", request.getPassword()).ifPresent(errors::add);
        validationRules.validateId("userId", request.getUserId()).ifPresent(errors::add);

        return errors;
    }
}
