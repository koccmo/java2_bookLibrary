package lv.estore.app.core.services.products;

import lv.estore.app.core.domain.Product;
import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.database.products.ProductRepository;
import lv.estore.app.core.request.products.ProductNameRequest;
import lv.estore.app.core.responses.products.GetProductsResponse;
import lv.estore.app.core.validators.products.ProductNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindProductsByNameService {

    @Autowired
    ProductRepository database;

    @Autowired
    ProductNameValidator validator;

    public GetProductsResponse execute(final ProductNameRequest request) {
        final List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new GetProductsResponse(errors, null);
        }
        List<Product> products = database.findProductsByName(request.getName());
        return new GetProductsResponse(null, products);
    }
}
