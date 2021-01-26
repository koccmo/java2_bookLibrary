package estore.core.service;

import estore.core.requests.UpdateProductByIdRequest;
import estore.core.responses.UpdateProductByIdResponse;
import estore.core.validation.CoreError;
import estore.core.validation.UpdateProductByIdValidator;
import estore.database.ProductDB;
import estore.domain.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateProductByIdService {

    private ProductDB productDB;
    private UpdateProductByIdValidator validator;

    public UpdateProductByIdService(ProductDB productDB, UpdateProductByIdValidator validator) {
        this.productDB = productDB;
        this.validator = validator;
    }

    public UpdateProductByIdResponse execute(UpdateProductByIdRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new UpdateProductByIdResponse(errors);
        }

        Product productToUpdate = new Product(
                request.getProductName(),
                request.getProductDescription(),
                request.getProductCategory(),
                Integer.valueOf(request.getProductQuantity()),
                Double.valueOf(request.getProductPrice()));
        productToUpdate.setId(request.getProductId());
        productDB.updateProduct(productToUpdate);
        return new UpdateProductByIdResponse(productToUpdate, 1);
    }

}
