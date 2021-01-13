package internet_store.core.services.product.validators;

import internet_store.core.requests.product.DeleteProductByOtherRequest;
import internet_store.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteByOtherRequestValidator {

    public List<CoreError> validate (DeleteProductByOtherRequest deleteProductByOtherRequest){

        List<CoreError> errors = new ArrayList<>();

        if ((deleteProductByOtherRequest.getTitle() == null) || (deleteProductByOtherRequest.getTitle().isEmpty())){
            errors.add(new CoreError("title", "Not valid input for title"));
        }
        if((deleteProductByOtherRequest.getDescription() == null) || (deleteProductByOtherRequest.getTitle().isEmpty())){
            errors.add(new CoreError("title", "Not valid input for description"));
        }
        if((deleteProductByOtherRequest.getStartPrice() == null) && (deleteProductByOtherRequest.getEndPrice() == null)){
            errors.add(new CoreError("title", "Not valid input for start price and end price"));
        }
        return errors;
    }

}
