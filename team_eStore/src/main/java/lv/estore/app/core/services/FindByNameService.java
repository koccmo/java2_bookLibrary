package lv.estore.app.core.services;

import lv.estore.app.core.domain.Product;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.repository.ProductDatabase;
import lv.estore.app.core.request.NameRequest;
import lv.estore.app.core.responses.FindResponse;
import lv.estore.app.core.validators.NameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindByNameService {

    @Autowired ProductDatabase database;
    @Autowired
    NameValidator validator;

    /**
     * Method to find product by name.
     * @param request CoreRequest
     * @return CoreResponse
     */
    public FindResponse execute(final NameRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindResponse(errors);
        }
        Product product = database.findByName(request.getName());
        return new FindResponse(product);
    }
}
