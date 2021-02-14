package lv.estore.app.core.services.products;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.database.products.ProductRepository;
import lv.estore.app.core.request.products.ProductIdRequest;
import lv.estore.app.core.responses.products.RemoveProductResponse;
import lv.estore.app.core.validators.products.ProductIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static lv.estore.app.utils.CommonUtils.getLong;

@Component
public class RemoveProductByIdService {

    @Autowired
    ProductIdValidator validator;

    @Autowired
    ProductRepository database;

    public RemoveProductResponse execute(final ProductIdRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new RemoveProductResponse(errors);
        }
        boolean isRemoved = database.removeProductById(getLong(request.getId()));
        return new RemoveProductResponse(isRemoved);
    }
}
