package internet_store.core.services.product.validators;

import internet_store.core.requests.product.AddProductRequest;
import internet_store.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddProductRequestValidator {

    public List<CoreError> validate (AddProductRequest addProductRequest){
        List <CoreError> errors = new ArrayList<>();

        if (addProductRequest.getProduct().getTitle() == null || addProductRequest.getProduct().getTitle().isEmpty()){
            errors.add(new CoreError("title", "Not valid input for title"));
        }
        if (addProductRequest.getProduct().getDescription() == null || addProductRequest.getProduct().getDescription().isEmpty()){
            errors.add(new CoreError("description", "Not valid input for description"));
        }
        if (addProductRequest.getProduct().getPrice() < 0){
            errors.add(new CoreError("price", "Not valid input for price"));
        }
        return errors;
    }
}
