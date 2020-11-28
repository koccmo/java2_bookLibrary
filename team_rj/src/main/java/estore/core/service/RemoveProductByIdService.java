package estore.core.service;

import estore.core.validation.CoreError;
import estore.core.validation.RemoveProductByIdValidator;
import estore.database.ProductDB;
import estore.core.requests.RemoveProductByIdRequest;
import estore.core.responses.RemoveProductByIdResponse;

import java.util.List;

public class RemoveProductByIdService {

    private ProductDB productDatabase;
    private RemoveProductByIdValidator validator;

    public RemoveProductByIdService(ProductDB productDatabase, RemoveProductByIdValidator validator) {
        this.productDatabase = productDatabase;
        this.validator = validator;
    }

    public RemoveProductByIdResponse execute(RemoveProductByIdRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new RemoveProductByIdResponse(errors);
        }

        int productsRemoved = productDatabase.removeProductById(Long.valueOf(request.getProductId()));
        return new RemoveProductByIdResponse(productsRemoved);
    }

}
