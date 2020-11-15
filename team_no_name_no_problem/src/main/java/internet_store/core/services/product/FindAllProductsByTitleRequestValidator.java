package internet_store.core.services.product;

import internet_store.core.requests.product.FindAllByTitleRequest;
import internet_store.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;

public class FindAllProductsByTitleRequestValidator {

    public List<CoreError> validate (FindAllByTitleRequest findAllByTitleRequest){

        List <CoreError> errors = new ArrayList<>();

        if (findAllByTitleRequest.getTitle() == null || findAllByTitleRequest.getTitle().isEmpty()){
            errors.add(new CoreError("title", "Not valid input for title"));
        }

        return errors;

    }
}
