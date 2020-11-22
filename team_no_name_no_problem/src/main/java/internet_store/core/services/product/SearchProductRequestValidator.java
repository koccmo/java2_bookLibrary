package internet_store.core.services.product;

import internet_store.core.requests.product.SearchProductRequest;
import internet_store.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;

public class SearchProductRequestValidator {

    public List<CoreError> validate (SearchProductRequest searchProductRequest){

        List <CoreError> errors = new ArrayList<>();

        if (isTitleAndDescriptionEmpty(searchProductRequest.getTitle(), searchProductRequest.getDescription())){
            errors.add(new CoreError("title & description", "Not valid input for search"));
        }

        return errors;
    }

    private boolean isTitleAndDescriptionEmpty(String title, String description){
        return (title == null || title.isEmpty()) && (description == null || description.isEmpty());
    }
}
