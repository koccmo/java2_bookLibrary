package estore.core.service;

import estore.core.validation.CoreError;
import estore.core.validation.RemoveProductByNameValidator;
import estore.database.ProductDataBase;
import estore.core.requests.RemoveProductByNameRequest;
import estore.core.responses.RemoveProductByNameResponse;

import java.util.List;

public class RemoveProductByNameService {

    private ProductDataBase database;
    private RemoveProductByNameValidator validator;

    public RemoveProductByNameService(ProductDataBase database, RemoveProductByNameValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public RemoveProductByNameResponse execute(RemoveProductByNameRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new RemoveProductByNameResponse(errors);
        }

        int productsRemoved = database.removeProductByName(request.getProductName());
        return new RemoveProductByNameResponse(productsRemoved);
    }

}
