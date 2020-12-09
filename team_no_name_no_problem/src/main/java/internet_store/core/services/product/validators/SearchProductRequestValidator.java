package internet_store.core.services.product.validators;

import internet_store.core.requests.product.SearchProductRequest;
import internet_store.core.response.CoreError;
import internet_store.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class SearchProductRequestValidator {

    public List<CoreError> validate (SearchProductRequest searchProductRequest){

        List <CoreError> errors = new ArrayList<>();

        if (isTitleAndDescriptionEmpty(searchProductRequest.getTitle(), searchProductRequest.getDescription())){
            errors.add(new CoreError("search", "Not valid input for search"));
        }

        if (isNotValidInputForOrdering(searchProductRequest)){
            errors.addAll(updateErrorsListForOrdering(searchProductRequest));
        }

        if (isNotValidRequestForPaging(searchProductRequest)){
            errors.addAll(updateErrorsListForPaging(searchProductRequest));
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

    private boolean isNotValidRequestForPaging (SearchProductRequest searchProductRequest){
        return (!searchProductRequest.getPaging().isFilledBoth() &&
                !searchProductRequest.getPaging().isEmptyBoth()) ||
                (isNotValidInputForPageNumber(searchProductRequest)) ||
                isNotValidInputForPageSize(searchProductRequest);
    }

    private boolean isNotValidInputForPageNumber(SearchProductRequest searchProductRequest){
        return searchProductRequest.getPaging().getPageNumber() <= 0;
    }

    private boolean isNotValidInputForPageSize(SearchProductRequest searchProductRequest){
        return searchProductRequest.getPaging().getPageSize() <= 0;
    }

    private List<CoreError> updateErrorsListForPaging(SearchProductRequest searchProductRequest){

        List<CoreError>errors = new ArrayList<>();

        if (!searchProductRequest.getPaging().isFilledBoth() &&
                !searchProductRequest.getPaging().isEmptyBoth()) {
            errors.add(new CoreError("search", "Not valid input for paging parameters"));
        }else {
            if (isNotValidInputForPageNumber(searchProductRequest)) {
                errors.add(new CoreError("pageNumber", "Not valid input for page number"));
            }
            if (isNotValidInputForPageSize(searchProductRequest)) {
                errors.add(new CoreError("pageSize", "Not valid input for page size"));
            }
        }
        return  errors;
    }
}
