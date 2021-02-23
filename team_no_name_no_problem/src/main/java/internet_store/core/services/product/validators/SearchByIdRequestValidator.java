package internet_store.core.services.product.validators;

import internet_store.core.requests.product.SearchProductByIdRequest;
import internet_store.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchByIdRequestValidator {

    public List<CoreError> validate (SearchProductByIdRequest findByIdRequest){
        List<CoreError>errors = new ArrayList<>();

        if ((findByIdRequest.getId() == null) || (findByIdRequest.getId() < 1)){
            errors.add(new CoreError("id", "Not valid input for id"));
        }

        return errors;
    }

}
