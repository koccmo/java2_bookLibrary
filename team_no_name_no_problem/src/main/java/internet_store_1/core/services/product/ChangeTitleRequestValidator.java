package internet_store_1.core.services.product;

import internet_store_1.core.requests.product.ChangeTitleRequest;
import internet_store_1.core.response.CoreError;


import java.util.ArrayList;
import java.util.List;

public class ChangeTitleRequestValidator {

    public List<CoreError> validate (ChangeTitleRequest changeTitleRequest){

        List<CoreError> errors = new ArrayList<>();

        if (changeTitleRequest.getId() < 1){
            errors.add(new CoreError("id", "Not valid input for id"));
        }

        if (changeTitleRequest.getTitle() == null || changeTitleRequest.getTitle().isEmpty()){
            errors.add(new CoreError("title", "Not Valid input for title"));
        }

        return errors;
    }
}
