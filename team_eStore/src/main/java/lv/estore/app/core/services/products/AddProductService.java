package lv.estore.app.core.services.products;

import lv.estore.app.core.database.products.ProductRepository;
import lv.estore.app.core.domain.Product;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.products.AddProductRequest;
import lv.estore.app.core.responses.products.AddProductResponse;
import lv.estore.app.core.validators.products.AddProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static lv.estore.app.utils.CommonUtils.getBigDecimal;

@Component
public class AddProductService {

    @Autowired
    AddProductValidator validator;

    @Autowired
    ProductRepository database;

    public AddProductResponse execute(final AddProductRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddProductResponse(errors);
        } else {
            Product product = new Product(request.getName(),
                    request.getDescription(),
                    getBigDecimal(request.getPrice()));
            Long isAdded = database.addProduct(product);
            return new AddProductResponse(isAdded != 0);
        }
    }
}
