package lv.estore.app.core.services.deals;

import lv.estore.app.core.database.deals.DealsRepository;
import lv.estore.app.core.domain.Deal;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.deals.AddDealRequest;
import lv.estore.app.core.responses.deals.AddDealResponse;
import lv.estore.app.core.validators.deals.AddDealValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static lv.estore.app.utils.CommonUtils.getLong;
import static lv.estore.app.utils.CommonUtils.transformDate;

@Component
public class AddDealService {

    @Autowired
    AddDealValidator validator;

    @Autowired
    DealsRepository database;

    public AddDealResponse execute(final AddDealRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddDealResponse(errors);
        } else {
            Deal deal = new Deal(getLong(request.getUserId()),
                    getLong(request.getProductId()),
                    "completed",
                    transformDate(LocalDateTime.now()));
            Long isAdded = database.addDeal(deal);
            return new AddDealResponse(isAdded != 0);
        }
    }
}
