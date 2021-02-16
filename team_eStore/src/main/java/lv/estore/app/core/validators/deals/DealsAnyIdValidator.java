package lv.estore.app.core.validators.deals;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.deals.AnyIdInDealRequest;
import lv.estore.app.core.validators.common_validation_rules.ValidationRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DealsAnyIdValidator {

    @Autowired
    ValidationRules validationRules;

    public List<CoreError> validate(final AnyIdInDealRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validationRules.validateId("dealId", request.getId()).ifPresent(errors::add);
        return errors;
    }
}
