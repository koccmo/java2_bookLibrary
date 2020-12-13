package internet_store.core.services.product.validators;

import internet_store.core.requests.product.FindByIdRequest;
import internet_store.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FindByIdRequestValidator {

    public List<CoreError> validate (FindByIdRequest findByIdRequest){
        List<CoreError>errors = new ArrayList<>();

        if ((findByIdRequest.getId() == null) || (findByIdRequest.getId() < 1)){
            errors.add(new CoreError("id", "Not valid input for id"));
        }

        return errors;
    }

}
