package lv.estore.app.core.services;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.database.iDatabase;
import lv.estore.app.core.request.IdRequest;
import lv.estore.app.core.responses.FindByIdResponse;
import lv.estore.app.core.validators.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindByIdService {

    @Autowired
    IdValidator validator;

    @Autowired
    iDatabase database;

    /**
     * Method to find product by 'Id'.
     * @param request FindByIdRequest
     * @return response FindResponse
     */
    public FindByIdResponse execute(final IdRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindByIdResponse(errors);
        }
        return new FindByIdResponse(database.findById(Long.parseLong(request.getId())));
    }
}
