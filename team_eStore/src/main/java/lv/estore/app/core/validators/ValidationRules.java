package lv.estore.app.core.validators;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.Ordering;
import lv.estore.app.core.request.Paging;
import lv.estore.app.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidationRules {

    @Autowired
    ValidationUtils validationUtils;

    public Optional<CoreError> validateId(final String value){
        if (validationUtils.isEmptyField(value)) {
            return Optional.of(new CoreError("Id",  "Field should not be empty"));
        } else {
            if (validationUtils.isValidId(value)) {
                return Optional.empty();
            } else {
                return Optional.of(new CoreError("Id",  "Value should be valid"));
            }
        }
    }

    public Optional<CoreError> validateName(final String value){
        return validationUtils.isEmptyField(value)
                ? Optional.of(new CoreError("Name",  "Field should not be empty"))
                : Optional.empty();
    }

    public Optional<CoreError> validateDescription(final String value){
        return validationUtils.isEmptyField(value)
            ? Optional.of(new CoreError("Description",  "Field should not be empty"))
            : Optional.empty();
    }

    public Optional<CoreError> validatePrice(final String value) {
        if (validationUtils.isEmptyField(value)) {
            return Optional.of(new CoreError("Price",  "Field should not be empty"));
        } else {
            if (!validationUtils.isValidPrice(value)) {
                return Optional.of(new CoreError("Price", "Field should be valid"));
            } else {
                return Optional.empty();
            }
        }
    }

    public Optional<CoreError> validateOrderBy(final Ordering ordering) {
        return (ordering == null || validationUtils.isValidOrderBy(ordering.getOrderBy()))
            ? Optional.empty()
            : Optional.of(new CoreError("OrderBy", "Field value should be valid 'name'/'price'"));
    }

    public Optional <CoreError> validateDirection(final Ordering ordering){
        return (ordering == null || validationUtils.isValidDirection(ordering.getOrderDirection()))
                ? Optional.empty()
                : Optional.of(new CoreError("Direction", "Value should be valid: 'ASCENDING' or 'DESCENDING'"));
    }

    public Optional <CoreError> validatePageNumber(final Paging paging){
        return (paging == null || validationUtils.isValidPagingParameters(paging.getPageNumber()))
                ? Optional.empty()
                : Optional.of(new CoreError("PageNumber", "Value should be valid '>=0'"));
    }

    public Optional <CoreError> validatePageSize(final Paging paging){
        return (paging == null || validationUtils.isValidPagingParameters(paging.getPageSize()))
            ? Optional.empty()
            : Optional.of(new CoreError("PageSize", "Value should be valid '>=0'"));
    }
}
