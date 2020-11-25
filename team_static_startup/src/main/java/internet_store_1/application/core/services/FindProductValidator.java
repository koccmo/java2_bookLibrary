package internet_store_1.application.core.services;

import internet_store_1.application.core.requests.FindByProductNameRequest;
import internet_store_1.application.core.responses.CoreError;

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
