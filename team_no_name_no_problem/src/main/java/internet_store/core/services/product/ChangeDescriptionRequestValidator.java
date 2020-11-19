package internet_store.core.services.product;

import internet_store.core.requests.product.ChangeDescriptionRequest;
import internet_store.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;

public class ChangeDescriptionRequestValidator {

    public List<CoreError> validate (ChangeDescriptionRequest changeDescriptionRequest){

        List <CoreError> errors = new ArrayList<>();

        if (changeDescriptionRequest.getId() < 1){
            errors.add(new CoreError("id", "Not valid input for id"));
        }

        if (changeDescriptionRequest.getDescription() == null || changeDescriptionRequest.getDescription().isEmpty()){
            errors.add(new CoreError("description", "Not valid input for description"));
        }

        return errors;
    }

}
