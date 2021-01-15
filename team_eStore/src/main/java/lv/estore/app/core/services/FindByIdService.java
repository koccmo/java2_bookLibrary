package lv.estore.app.core.services;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.repository.ProductDatabase;
import lv.estore.app.core.request.IdRequest;
import lv.estore.app.core.responses.FindResponse;
import lv.estore.app.core.validators.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindByIdService {

    @Autowired
    IdValidator validator;
    @Autowired ProductDatabase database;

    /**
     * Method to find product by 'Id'.
     * @param request FindByIdRequest
     * @return response FindResponse
     */
    public FindResponse execute(final IdRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindResponse(errors);
        }
        return new FindResponse(database.findById(Long.parseLong(request.getId())));
    }
}
