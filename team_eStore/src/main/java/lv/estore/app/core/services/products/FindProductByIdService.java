package lv.estore.app.core.services.products;

import lv.estore.app.core.database.products.ProductRepository;
import lv.estore.app.core.domain.Product;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.products.ProductIdRequest;
import lv.estore.app.core.responses.products.FindProductByIdResponse;
import lv.estore.app.core.validators.products.ProductIdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindProductByIdService {

    @Autowired
    ProductIdValidator validator;

    @Autowired
    ProductRepository database;

    public FindProductByIdResponse execute(final ProductIdRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new FindProductByIdResponse(errors);
        }
        //List<Product> products = new ArrayList<>();
        Product product = database.findProductById(Long.parseLong(request.getId()));
        //products.add(product);
            return new FindProductByIdResponse(product);
    }
}
