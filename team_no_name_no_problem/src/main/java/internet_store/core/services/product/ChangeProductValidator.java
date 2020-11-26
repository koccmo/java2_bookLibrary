package internet_store.core.services.product;

import internet_store.core.requests.product.ChangeProductRequest;
import internet_store.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;

public class ChangeProductValidator {

    public List<CoreError> validate (ChangeProductRequest changeProductRequest){

        List <CoreError> errors = new ArrayList<>();

        if ((changeProductRequest.getId() == null) || (changeProductRequest.getId() < 1)){
            errors.add(new CoreError("id", "Not valid input for id"));
        }
        if ((changeProductRequest.getPrice() == null) || (changeProductRequest.getPrice() < 1)) {
            errors.add(new CoreError("price", "Not valid input for price"));
        }

        return errors;
    }

}
