package lv.estore.app.core.services.deals;

import lv.estore.app.core.database.deals.DealsRepository;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.deals.UpdateDealByIdRequest;
import lv.estore.app.core.responses.deals.UpdateDealResponse;
import lv.estore.app.core.validators.deals.DealUpdateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static lv.estore.app.utils.CommonUtils.getLong;

@Component
public class UpdateDealByIdService {

    @Autowired
    DealsRepository database;

    @Autowired
    DealUpdateValidator validator;

    public UpdateDealResponse execute(final UpdateDealByIdRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateDealResponse(errors);
        }
        Long id = getLong(request.getId());
        Long userId = getLong(request.getUserId());
        Long productId = getLong(request.getProductId());
        String status = request.getStatus();
        boolean isUpdated = database.updateDealById(id, userId, productId, status);
        return new UpdateDealResponse(isUpdated);
    }
}
