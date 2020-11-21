package estore.core.service;

import estore.core.validation.CoreError;
import estore.core.validation.RemoveProductByIdValidator;
import estore.database.ProductDataBase;
import estore.core.requests.RemoveProductByIdRequest;
import estore.core.responses.RemoveProductByIdResponse;

import java.util.List;

public class RemoveProductByIdService {

    private ProductDataBase database;
    private RemoveProductByIdValidator validator;

    public RemoveProductByIdService(ProductDataBase database, RemoveProductByIdValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public RemoveProductByIdResponse execute(RemoveProductByIdRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new RemoveProductByIdResponse(errors);
        }

        int productsRemoved = database.removeProductById(Long.valueOf(request.getProductId()));
        return new RemoveProductByIdResponse(productsRemoved);
    }

}
