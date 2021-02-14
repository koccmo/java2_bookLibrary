package lv.estore.app.core.validators.products;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.products.ProductNameRequest;
import lv.estore.app.core.validators.common_validation_rules.ValidationRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductNameValidator {

    @Autowired
    ValidationRules validationRules;

    public List<CoreError> validate(final ProductNameRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validationRules.validateTextField("productName", request.getName()).ifPresent(errors::add);
        return errors;
    }
}
