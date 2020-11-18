package internet_store.application.core.services;

import internet_store.application.core.requests.FindByProductNameRequest;
import internet_store.application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class FindProductValidator {

    public List<CoreError> validate(FindByProductNameRequest request){
        List<CoreError> errors = new ArrayList<>();

        if (request.getProductName() ==null || request.getProductName().isEmpty()){
            errors.add(new CoreError("Product Name", "Should be valid and not empty."));
        }

        return errors;
    }


}
