package lv.estore.app.core.validators;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.IdRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class IdValidator {

    @Autowired
    ValidationRules validationRules;

    public List<CoreError> validate(final IdRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validationRules.validateId(request.getId()).ifPresent(errors::add);
        return errors;
    }
}
