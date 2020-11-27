package internet_store.lesson_3.core.services.validators;

import internet_store.lesson_3.core.requests.FindByProductNameRequest;
import internet_store.lesson_3.core.responses.CoreError;

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
