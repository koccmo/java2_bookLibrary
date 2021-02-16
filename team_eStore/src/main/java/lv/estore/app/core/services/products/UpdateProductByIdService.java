package lv.estore.app.core.services.products;

import lv.estore.app.core.database.products.ProductRepository;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.products.UpdateProductByIdRequest;
import lv.estore.app.core.responses.products.UpdateProductResponse;
import lv.estore.app.core.validators.products.ProductUpdateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static lv.estore.app.utils.CommonUtils.getBigDecimal;
import static lv.estore.app.utils.CommonUtils.getLong;

@Component
public class UpdateProductByIdService {

    @Autowired
    ProductRepository database;

    @Autowired
    ProductUpdateValidator validator;

    public UpdateProductResponse execute(final UpdateProductByIdRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new UpdateProductResponse(errors);
        }
        boolean isUpdated = database.updateProductById(getLong(request.getId()),
                request.getName(),
                request.getDescription(),
                getBigDecimal(request.getPrice()));
        return new UpdateProductResponse(isUpdated);
    }
}
