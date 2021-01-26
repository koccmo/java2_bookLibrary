package internet_store.application.core.services.product.validators;

import internet_store.application.core.requests.product.DeleteByProductNameRequest;
import internet_store.application.core.responses.product.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteByProductNameValidator {

    public List<CoreError> validate (DeleteByProductNameRequest request){
        List<CoreError> errors = new ArrayList<>();

        String productName = request.getProductName();
        if(productName == null || productName.isEmpty()){
            errors.add(new CoreError("Product name", "must not be empty"));
        }
        return errors;
    }

}
