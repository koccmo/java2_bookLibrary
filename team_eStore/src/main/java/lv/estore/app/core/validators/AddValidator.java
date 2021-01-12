package lv.estore.app.core.validators;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.AddRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddValidator {

    @Autowired ValidationRules validationRules;

    public List<CoreError> validate(final AddRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validationRules.validateName(request.getName()).ifPresent(errors::add);
        validationRules.validateDescription(request.getDescription()).ifPresent(errors::add);
        validationRules.validatePrice(request.getPrice()).ifPresent(errors::add);
        return errors;
    }
}
