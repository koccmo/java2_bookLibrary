package lv.estore.app.core.validators;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateValidator {

    @Autowired
    ValidationRules validationRules;

    public List<CoreError> validate(final UpdateRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validationRules.validateId(request.getId()).ifPresent(errors::add);
        validationRules.validateName(request.getName()).ifPresent(errors::add);
        validationRules.validateDescription(request.getDescription()).ifPresent(errors::add);
        validationRules.validatePrice(request.getPrice()).ifPresent(errors::add);
        return errors;
    }
}
