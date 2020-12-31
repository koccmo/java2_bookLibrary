package estore.core.service;

import estore.core.validation.CoreError;
import estore.core.validation.RemoveProductByNameValidator;
import estore.database.ProductDB;
import estore.core.requests.RemoveProductByNameRequest;
import estore.core.responses.RemoveProductByNameResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RemoveProductByNameService {

    @Autowired
    private ProductDB productDB;
    @Autowired
    private RemoveProductByNameValidator validator;

//    public RemoveProductByNameService(ProductDB productDB, RemoveProductByNameValidator validator) {
//        this.productDB = productDB;
//        this.validator = validator;
//    }

    public RemoveProductByNameResponse execute(RemoveProductByNameRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new RemoveProductByNameResponse(errors);
        }

        int productsRemoved = productDB.removeProductByName(request.getProductName());
        return new RemoveProductByNameResponse(productsRemoved);
    }

}
