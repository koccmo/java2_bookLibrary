package estore.core.service;

import estore.core.validation.CoreError;
import estore.core.validation.AddProductValidator;
import estore.database.ProductRepository;
import estore.core.model.Product;
import estore.core.requests.AddProductRequest;
import estore.core.responses.AddProductResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddProductService {

    private ProductRepository productDB;
    private AddProductValidator validator;

    public AddProductService(ProductRepository productDB,
                             AddProductValidator validator) {
        this.productDB = productDB;
        this.validator = validator;
    }

    public AddProductResponse execute(AddProductRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new AddProductResponse(errors);
        }

        Product product = new Product(request.getProductName(), request.getProductDescription(), request.getProductCategory());

        productDB.addNewProduct(product);
        AddProductResponse response = new AddProductResponse(product);
        response.setSuccessfullyAdded(true);

        return response;
    }

}
