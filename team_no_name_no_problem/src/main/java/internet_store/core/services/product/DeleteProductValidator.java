package internet_store.core.services.product;

import internet_store.core.requests.product.DeleteProductRequest;
import internet_store.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;

public class DeleteProductValidator {

    public List<CoreError> validate (DeleteProductRequest deleteProductRequest){

        List<CoreError> errors = new ArrayList<>();

        if (deleteProductRequest.getId() < 1){
            errors.add(new CoreError("id", "Not valid input for id"));
        }

        return errors;

    }

}
