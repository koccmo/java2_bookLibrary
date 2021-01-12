package lv.estore.app.core.validators;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.NameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NameValidator {

    @Autowired
    ValidationRules validationRules;

    public List<CoreError> validate(final NameRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validationRules.validateName(request.getName()).ifPresent(errors::add);
        return errors;
    }
}
