package lv.estore.app.core.services;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.database.iDatabase;
import lv.estore.app.core.request.UpdateRequest;
import lv.estore.app.core.responses.UpdateResponse;
import lv.estore.app.core.validators.UpdateValidator;
import lv.estore.app.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateByIdService {

    @Autowired
    iDatabase database;

    @Autowired
    UpdateValidator validator;

    @Autowired
    CommonUtils commonUtils;

    /**
     * Method to update product info by ID.
     * @param request UpdateRequest
     * @return response UpdateByIdResponse
     */
    public UpdateResponse execute(final UpdateRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateResponse(errors);
        }
        boolean isUpdated = database.updateById(Long.parseLong(request.getId()),
                request.getName(),
                request.getDescription(),
                commonUtils.createBigDecimal(request.getPrice()));
        return new UpdateResponse(isUpdated);
    }
}
