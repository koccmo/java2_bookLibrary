package lv.estore.app.core.validators.users;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.users.AddUserRequest;
import lv.estore.app.core.validators.common_validation_rules.ValidationRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddUserValidator {

    @Autowired
    ValidationRules validationRules;

    public List<CoreError> validate(final AddUserRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validationRules.validateTextField("firstName", request.getFirstName()).ifPresent(errors::add);
        validationRules.validateTextField("lastName", request.getLastName()).ifPresent(errors::add);
        validationRules.validateTextField("email", request.getEmail()).ifPresent(errors::add);
        validationRules.validateTextField("password", request.getPassword()).ifPresent(errors::add);
        return errors;
    }
}
