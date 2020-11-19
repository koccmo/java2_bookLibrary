package internet_store.application.core.services;

import internet_store.application.core.requests.DeleteByProductIdRequest;
import internet_store.application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class DeleteByProductIdValidator {

    public List<CoreError> validate (DeleteByProductIdRequest request){
        List<CoreError> errors = new ArrayList<>();
        Long productId = request.getProductId();

        if(productId == null){
            errors.add(new CoreError("Product Id", "Product Id must not be empty."));
        }
        return errors;
    }

}
