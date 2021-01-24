package estore.core.validation;

import estore.core.requests.Ordering;
import estore.core.requests.SearchProductByNameRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SearchProductByNameValidator {

    private ValidationRules validationRules;

    public SearchProductByNameValidator(ValidationRules validationRules) {
        this.validationRules = validationRules;
    }

    public List<CoreError> validate(SearchProductByNameRequest request) {
        List<CoreError> errors = new ArrayList<CoreError>();

        validateProductNameIfEmpty(request).ifPresent(errors::add);
        validateProductNameUnallowedPattern(request).ifPresent(errors::add);
        if (request.getOrdering() != null) {
            validateOrderBy(request.getOrdering()).ifPresent(errors::add);
            validateOrderDirection(request.getOrdering()).ifPresent(errors::add);
            validateMandatoryOrderBy(request.getOrdering()).ifPresent(errors::add);
            validateMandatoryOrderDirection(request.getOrdering()).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateProductNameIfEmpty(SearchProductByNameRequest request) {
        return (request.getProductName() == null || request.getProductName().isEmpty())
                ? Optional.of(new CoreError("Product Name", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductNameUnallowedPattern(SearchProductByNameRequest request) {
        return (!validationRules.validateString(request.getProductName()))
                ? Optional.of(new CoreError("Product Name", "Must contain only english letters!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderBy(Ordering ordering) {
        return (ordering.getOrderBy() != null
                && !(ordering.getOrderBy().toLowerCase().equals("name") ||
                ordering.getOrderBy().toLowerCase().equals("price")))
                ? Optional.of(new CoreError("orderBy", "Must contain 'name' or 'price' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateOrderDirection(Ordering ordering) {
        return (ordering.getOrderDirection() != null
                && !(ordering.getOrderDirection().toLowerCase().equals("ascending") ||
                ordering.getOrderDirection().toLowerCase().equals("asc") ||
                ordering.getOrderDirection().toLowerCase().equals("descending") ||
                ordering.getOrderDirection().toLowerCase().equals("desc")))
                ? Optional.of(new CoreError("orderDirection", "Must contain 'ASCENDING/asc' or 'DESCENDING/desc' only!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderBy(Ordering ordering) {
        return (ordering.getOrderDirection() != null && ordering.getOrderBy() == null)
                ? Optional.of(new CoreError("orderBy", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateMandatoryOrderDirection(Ordering ordering) {
        return (ordering.getOrderBy() != null && ordering.getOrderDirection() == null)
                ? Optional.of(new CoreError("orderDirection", "Must not be empty!"))
                : Optional.empty();
    }
}
