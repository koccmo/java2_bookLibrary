package lv.estore.app.core.validators.common_validation_rules;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.products.Ordering;
import lv.estore.app.core.request.products.Paging;
import lv.estore.app.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidationRules {

    @Autowired
    ValidationUtils validationUtils;

    public Optional<CoreError> validateId(final String fieldName, final String value){
        if (validationUtils.isEmptyField(value)) {
            return Optional.of(new CoreError(fieldName,  "Field should not be empty"));
        } else {
            if (validationUtils.isValidId(value)) {
                return Optional.empty();
            } else {
                return Optional.of(new CoreError(fieldName,  "Value should be valid"));
            }
        }
    }

    public Optional<CoreError> validateTextField(final String fieldName, final String value){
        return validationUtils.isEmptyField(value)
                ? Optional.of(new CoreError(fieldName,  "Field should not be empty"))
                : Optional.empty();
    }

    public Optional<CoreError> validatePrice(final String value) {
        if (validationUtils.isEmptyField(value)) {
            return Optional.of(new CoreError("productPrice",  "Field should not be empty"));
        } else {
            if (!validationUtils.isValidPrice(value)) {
                return Optional.of(new CoreError("productPrice", "Field should be valid"));
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
