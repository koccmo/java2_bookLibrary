package estore.core.validation;

import estore.core.requests.Ordering;
import estore.core.requests.Paging;
import estore.core.requests.SearchProductByCategoryRequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SearchProductByCategoryValidator {

    private ValidationRules validationRules;

    public SearchProductByCategoryValidator(ValidationRules validationRules) {
        this.validationRules = validationRules;
    }

    public List<CoreError> validate(SearchProductByCategoryRequest request) {
        List<CoreError> errors = new ArrayList<CoreError>();

        validateProductCategoryIfEmpty(request).ifPresent(errors::add);
        validateProductCategoryUnallowedPattern(request).ifPresent(errors::add);
        if (request.getOrdering() != null) {
            validateOrderBy(request.getOrdering()).ifPresent(errors::add);
            validateOrderDirection(request.getOrdering()).ifPresent(errors::add);
            validateMandatoryOrderBy(request.getOrdering()).ifPresent(errors::add);
            validateMandatoryOrderDirection(request.getOrdering()).ifPresent(errors::add);
            validatePagingPageNumberPattern(request.getPaging()).ifPresent(errors::add);
            validatePagingPageSizePattern(request.getPaging()).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validateProductCategoryIfEmpty(SearchProductByCategoryRequest request) {
        return (request.getProductCategory() == null || request.getProductCategory().isEmpty())
                ? Optional.of(new CoreError("Product category", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateProductCategoryUnallowedPattern(SearchProductByCategoryRequest request) {
        return (!validationRules.validateString(request.getProductCategory()))
                ? Optional.of(new CoreError("Product category", "Must contain only english letters!"))
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

    private Optional<CoreError> validatePagingPageNumberPattern(Paging paging) {
        return (!validationRules.validatePositiveInteger(paging.getPageNumber()))
                ? Optional.of(new CoreError("Page Number", "Must be positive integer!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePagingPageSizePattern(Paging paging) {
        return (!validationRules.validatePositiveInteger(paging.getPageSize()))
                ? Optional.of(new CoreError("Page Size", "Must be positive integer!"))
                : Optional.empty();
    }
}
