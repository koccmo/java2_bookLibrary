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

        if (isNotValidInputForOrdering(searchProductRequest)){
            errors.addAll(updateErrorsListForOrdering(searchProductRequest));
        }

        return errors;
    }

    private boolean isTitleAndDescriptionEmpty(String title, String description){
        return (title == null || title.isEmpty()) && (description == null || description.isEmpty());
    }

    private boolean isNotValidInputForOrdering(SearchProductRequest searchProductRequest){
        return (!searchProductRequest.getOrdering().filledBoth() &&
                !searchProductRequest.getOrdering().emptyBothFields()) ||
                (isNotValidInputForOrderBy(searchProductRequest)) ||
                (isNotValidInputForOrderDirection(searchProductRequest));
    }

    private boolean isNotValidInputForOrderBy(SearchProductRequest searchProductRequest){
        return !searchProductRequest.getOrdering().getOrderBy().equals("title") &&
                !searchProductRequest.getOrdering().getOrderBy().equals("description");
    }

    private boolean isNotValidInputForOrderDirection(SearchProductRequest searchProductRequest){
        return !searchProductRequest.getOrdering().getOrderDirection().equals("ASC") &&
                !searchProductRequest.getOrdering().getOrderDirection().equals("DESC");
    }

    private List<CoreError> updateErrorsListForOrdering(SearchProductRequest searchProductRequest){

        List<CoreError>errors = new ArrayList<>();

        if (!searchProductRequest.getOrdering().filledBoth() &&
                !searchProductRequest.getOrdering().emptyBothFields()) {
            errors.add(new CoreError("search", "Not valid input for ordering parameters"));
        }else {
            if (isNotValidInputForOrderBy(searchProductRequest) && searchProductRequest.getOrdering().filledBoth()) {
                errors.add(new CoreError("orderBy", "Not valid input for orderBy"));
            }
            if (isNotValidInputForOrderDirection(searchProductRequest) && searchProductRequest.getOrdering().filledBoth()) {
                errors.add(new CoreError("orderDirection", "Not valid input for orderDirection"));
            }
        }
        return  errors;
    }
}
