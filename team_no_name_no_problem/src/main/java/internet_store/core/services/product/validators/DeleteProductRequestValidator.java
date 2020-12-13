package internet_store.core.services.product.validators;

import internet_store.core.requests.product.DeleteProductRequest;
import internet_store.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteProductRequestValidator {

    public List<CoreError> validate (DeleteProductRequest deleteProductRequest){

        List<CoreError> errors = new ArrayList<>();

        if ((deleteProductRequest.getId() == null) || (deleteProductRequest.getId() < 1)){
            errors.add(new CoreError("id", "Not valid input for id"));
        }

        return errors;

    }

}
