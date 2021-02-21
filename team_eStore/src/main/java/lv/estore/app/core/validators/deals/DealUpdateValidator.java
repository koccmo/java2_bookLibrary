package lv.estore.app.core.validators.deals;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.deals.UpdateDealByIdRequest;
import lv.estore.app.core.validators.common_validation_rules.ValidationRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DealUpdateValidator {

    @Autowired
    ValidationRules validationRules;

    public List<CoreError> validate(final UpdateDealByIdRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validationRules.validateId("dealId", request.getId()).ifPresent(errors::add);
        validationRules.validateId("userId", request.getUserId()).ifPresent(errors::add);
        validationRules.validateId("productID", request.getProductId()).ifPresent(errors::add);
        validationRules.validateTextField("status", request.getStatus()).ifPresent(errors::add);
        return errors;
    }
}
