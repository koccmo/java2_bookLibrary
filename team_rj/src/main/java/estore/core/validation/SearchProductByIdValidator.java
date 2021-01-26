package estore.core.validation;

import estore.core.requests.SearchProductByIdRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SearchProductByIdValidator {

    private ValidationRules validationRules;

    public SearchProductByIdValidator(ValidationRules validationRules) {
        this.validationRules = validationRules;
    }

    public List<CoreError> validate(SearchProductByIdRequest request) {
        List<CoreError> errors = new ArrayList<CoreError>();

        validateProductIdIfEmpty(request).ifPresent(errors::add);
        validateProductIdUnallowedPattern(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateProductIdIfEmpty(SearchProductByIdRequest request) {
        return (request.getProductId() == null || request.getProductId().isEmpty())
                ? Optional.of(new CoreError("Product ID", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductIdUnallowedPattern(SearchProductByIdRequest request) {
        return (!validationRules.validatePositiveLong(request.getProductId()))
                ? Optional.of(new CoreError("Product ID", "Must contain only digits"))
                : Optional.empty();
    }
}
