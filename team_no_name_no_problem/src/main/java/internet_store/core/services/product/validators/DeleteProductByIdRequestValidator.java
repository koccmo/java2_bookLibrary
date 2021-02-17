package internet_store.core.services.product.validators;

import internet_store.core.requests.product.DeleteProductByIdRequest;
import internet_store.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteProductByIdRequestValidator {

    public List<CoreError> validate (DeleteProductByIdRequest deleteProductRequest){

        List<CoreError> errors = new ArrayList<>();

        if ((deleteProductRequest.getId() == null) || (deleteProductRequest.getId() < 1)){
            errors.add(new CoreError("id", "Not valid input for id"));
        }

        return errors;

    }

}
