package lv.estore.app.core.services;

import lv.estore.app.core.domain.Product;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.database.iDatabase;
import lv.estore.app.core.request.NameRequest;
import lv.estore.app.core.responses.FindByNameResponse;
import lv.estore.app.core.validators.NameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindByNameService {

    @Autowired
    iDatabase database;

    @Autowired
    NameValidator validator;

    /**
     * Method to find product by name.
     * @param request CoreRequest
     * @return CoreResponse
     */
    public FindByNameResponse execute(final NameRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindByNameResponse(errors, null);
        }
        List<Product> products = database.findByName(request.getName());
        return new FindByNameResponse(null, products);
    }
}
