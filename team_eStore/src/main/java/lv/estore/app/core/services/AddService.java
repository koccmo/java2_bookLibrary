package lv.estore.app.core.services;

import lv.estore.app.core.domain.Product;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.database.ProductRepository;
import lv.estore.app.core.request.AddRequest;
import lv.estore.app.core.responses.AddResponse;
import lv.estore.app.core.validators.AddValidator;
import lv.estore.app.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddService {

    @Autowired
    AddValidator validator;

    @Autowired
    ProductRepository database;

    @Autowired
    CommonUtils commonUtils;

    /** Method to add product.
    * @param request CoreRequest
    * @return CoreResponse
    */
    public AddResponse execute(final AddRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddResponse(errors);
        } else {
            Product product = new Product(request.getName(),
                    request.getDescription(),
                    commonUtils.createBigDecimal(request.getPrice()));
            boolean isAdded = database.addProduct(product);
            return new AddResponse(isAdded);
        }
    }
}
