package lv.estore.app.core.services;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.database.ProductRepository;
import lv.estore.app.core.request.IdRequest;
import lv.estore.app.core.responses.RemoveResponse;
import lv.estore.app.core.validators.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveByIdService {

    @Autowired
    IdValidator validator;

    @Autowired
    ProductRepository database;

    /**
     * Method to remove product by 'Id'.
     * @param request CoreRequest
     * @return CoreResponse
     */
    public RemoveResponse execute(final IdRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveResponse(errors);
        }
        boolean isRemoved = database.removeById(Long.parseLong(request.getId()));
        return new RemoveResponse(isRemoved);
    }
}
