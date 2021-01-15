package lv.estore.app.core.validators;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchValidator {

    @Autowired
    ValidationRules validationRules;

    public List<CoreError> validate(SearchRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getName()) && isEmpty(request.getPrice())) {
            errors.add(new CoreError("Name", "Field should not be empty"));
            errors.add(new CoreError("Price", "Field should not be empty"));
        } else if (isEmpty(request.getName()) && !isEmpty(request.getPrice())) {
            validationRules.validatePrice(request.getPrice()).ifPresent(errors::add);
            validationRules.validateOrderBy(request.getOrdering()).ifPresent(errors::add);
            validationRules.validateDirection(request.getOrdering()).ifPresent(errors::add);
            validationRules.validatePageSize(request.getPaging()).ifPresent(errors::add);
            validationRules.validatePageNumber(request.getPaging()).ifPresent(errors::add);
        } else if (!isEmpty(request.getName()) && isEmpty(request.getPrice())) {
            validationRules.validateName(request.getName()).ifPresent(errors::add);
            validationRules.validateOrderBy(request.getOrdering()).ifPresent(errors::add);
            validationRules.validateDirection(request.getOrdering()).ifPresent(errors::add);
            validationRules.validatePageSize(request.getPaging()).ifPresent(errors::add);
            validationRules.validatePageNumber(request.getPaging()).ifPresent(errors::add);
        } else {
            validationRules.validateName(request.getName()).ifPresent(errors::add);
            validationRules.validatePrice(request.getPrice()).ifPresent(errors::add);
            validationRules.validateOrderBy(request.getOrdering()).ifPresent(errors::add);
            validationRules.validateDirection(request.getOrdering()).ifPresent(errors::add);
            validationRules.validatePageSize(request.getPaging()).ifPresent(errors::add);
            validationRules.validatePageNumber(request.getPaging()).ifPresent(errors::add);
        }
        return errors;
    }

    private boolean isEmpty(String value) {
        return value == null || value.length() == 0;
    }
}
