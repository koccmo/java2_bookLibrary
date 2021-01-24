package estore.core.validation;

import estore.core.requests.RemoveProductByNameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class RemoveProductByNameValidator {

    private ValidationRules validationRules;

    public RemoveProductByNameValidator(ValidationRules validationRules) {
        this.validationRules = validationRules;
    }

    public List<CoreError> validate(RemoveProductByNameRequest request) {
        List<CoreError> errors = new ArrayList<CoreError>();

        validateProductNameIfEmpty(request).ifPresent(errors::add);
        validateProductNameUnallowedPattern(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductNameIfEmpty(RemoveProductByNameRequest request) {
        return (request.getProductName() == null || request.getProductName().isEmpty())
                ? Optional.of(new CoreError("Product Name", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductNameUnallowedPattern(RemoveProductByNameRequest request) {
        return (!validationRules.validateString(request.getProductName()))
                ? Optional.of(new CoreError("Product Name", "Must contain only english letters!"))
                : Optional.empty();
    }
}
