package estore.core.service;

import estore.core.validation.CoreError;
import estore.core.validation.RemoveProductByNameValidator;
import estore.database.ProductDB;
import estore.core.requests.RemoveProductByNameRequest;
import estore.core.responses.RemoveProductByNameResponse;
import estore.dependency_injection.DIComponent;
import estore.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class RemoveProductByNameService {

    @DIDependency
    private ProductDB productDB;
    @DIDependency
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
