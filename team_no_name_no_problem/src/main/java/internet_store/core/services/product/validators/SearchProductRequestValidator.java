package internet_store.core.services.product.validators;

import internet_store.core.requests.product.SearchProductRequest;
import internet_store.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchProductRequestValidator {

    public List<CoreError> validate (SearchProductRequest searchProductRequest){

        List <CoreError> errors = new ArrayList<>();

        if (isTitleAndDescriptionEmptyAndBothPricesEntered(searchProductRequest.getTitle(), searchProductRequest.getDescription(),
                                                           searchProductRequest.getStartPrice(),searchProductRequest.getEndPrice())){
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

    private boolean isTitleAndDescriptionEmptyAndBothPricesEntered(String title, String description,
                                                                   Integer startPrice, Integer endPrice){
        return (title == null || title.isEmpty()) && (description == null || description.isEmpty() &&
                startPrice == null || endPrice == null);
    }

    private boolean isNotValidInputForOrdering(SearchProductRequest searchProductRequest){
        return (!searchProductRequest.getOrdering().filledBoth() &&
                !searchProductRequest.getOrdering().emptyBothFields()) ||
                (isNotValidInputForOrderBy(searchProductRequest)) ||
                (isNotValidInputForOrderDirection(searchProductRequest));
    }

    private boolean isNotValidInputForOrderBy(SearchProductRequest searchProductRequest){
        return !searchProductRequest.getOrdering().getOrderBy().equals("title") &&
                !searchProductRequest.getOrdering().getOrderBy().equals("description") &&
                !searchProductRequest.getOrdering().getOrderBy().equals("start price") &&
                !searchProductRequest.getOrdering().getOrderBy().equals("end price");
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
