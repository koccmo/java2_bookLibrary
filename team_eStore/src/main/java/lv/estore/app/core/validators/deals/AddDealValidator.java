package lv.estore.app.core.validators.deals;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.deals.AddDealRequest;
import lv.estore.app.core.validators.common_validation_rules.ValidationRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddDealValidator {

    @Autowired
    ValidationRules validationRules;

    public List<CoreError> validate(final AddDealRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validationRules.validateId("userId", request.getUserId()).ifPresent(errors::add);
        validationRules.validateId("productId", request.getProductId()).ifPresent(errors::add);
        return errors;
    }
}
