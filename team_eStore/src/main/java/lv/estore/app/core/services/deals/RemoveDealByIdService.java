package lv.estore.app.core.services.deals;

import lv.estore.app.core.database.deals.DealsRepository;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.deals.AnyIdInDealRequest;
import lv.estore.app.core.responses.deals.RemoveDealResponse;
import lv.estore.app.core.validators.deals.DealsAnyIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static lv.estore.app.utils.CommonUtils.getLong;

@Component
public class RemoveDealByIdService {

    @Autowired
    DealsAnyIdValidator validator;

    @Autowired
    DealsRepository database;

    public RemoveDealResponse execute(final AnyIdInDealRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveDealResponse(errors);
        }
        boolean isRemoved = database.removeDealByID(getLong(request.getId()));
        return new RemoveDealResponse(isRemoved);
    }
}
