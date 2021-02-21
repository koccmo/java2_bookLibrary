package lv.estore.app.core.validators.products;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.products.SearchProductByNamePriceRequest;
import lv.estore.app.core.validators.common_validation_rules.ValidationRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductSearchValidator {

    @Autowired
    ValidationRules validationRules;

    public List<CoreError> validate(SearchProductByNamePriceRequest request) {
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
            validationRules.validateTextField("productName", request.getName()).ifPresent(errors::add);
            validationRules.validateOrderBy(request.getOrdering()).ifPresent(errors::add);
            validationRules.validateDirection(request.getOrdering()).ifPresent(errors::add);
            validationRules.validatePageSize(request.getPaging()).ifPresent(errors::add);
            validationRules.validatePageNumber(request.getPaging()).ifPresent(errors::add);
        } else {
            validationRules.validateTextField("productName", request.getName()).ifPresent(errors::add);
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
