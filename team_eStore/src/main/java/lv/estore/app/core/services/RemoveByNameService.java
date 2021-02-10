package lv.estore.app.core.services;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.database.iDatabase;
import lv.estore.app.core.request.NameRequest;
import lv.estore.app.core.responses.RemoveResponse;
import lv.estore.app.core.validators.NameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveByNameService {

    @Autowired
    iDatabase database;

    @Autowired
    NameValidator validator;

    /**
     * Method to remove product by name.
     * @param request RemoveNameRequest
     * @return RemoveResponse
     */
    public RemoveResponse execute(final NameRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
             return new RemoveResponse(errors);
        }
        boolean isRemoved = database.removeByName(request.getName());
        return new RemoveResponse(isRemoved);
    }
}
