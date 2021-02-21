package internet_store.core.services.product.validators;

import internet_store.core.requests.product.SearchProductByOtherRequest;
import internet_store.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchProductRequestValidator {

    public List<CoreError> validate (SearchProductByOtherRequest searchProductRequest){

        List <CoreError> errors = new ArrayList<>();

        if (isTitleAndDescriptionEmptyAndPriceRangeMissing(searchProductRequest.getTitle(), searchProductRequest.getDescription(),
                                                           searchProductRequest.getStartPrice(), searchProductRequest.getEndPrice())){
            errors.add(new CoreError("search", "Not valid input for search"));
            return errors;
        }

        if (isNotValidInputForPriceSearch(searchProductRequest)) {
            errors.add(new CoreError("price", "Not valid input for prices"));
        }

        if (isNotValidInputForOrdering(searchProductRequest)){
            errors.addAll(updateErrorsListForOrdering(searchProductRequest));
        }

        if (isNotValidRequestForPaging(searchProductRequest)){
            errors.addAll(updateErrorsListForPaging(searchProductRequest));
        }

        return errors;
    }

    private boolean isTitleAndDescriptionEmptyAndPriceRangeMissing(String title, String description,
                                                                   Integer startPrice, Integer endPrice){
        return (title == null || title.isEmpty()) && (description == null || description.isEmpty()) &&
                startPrice == null && endPrice == null;
    }

    private boolean isNotValidInputForPriceSearch(SearchProductByOtherRequest searchProductRequest) {
        if (searchProductRequest.getEndPrice() != null && searchProductRequest.getStartPrice() != null) {
            return pricesNegative(searchProductRequest) || !fieldsFilledCorrect(searchProductRequest);
        } else
        return !fieldsFilledCorrect(searchProductRequest);
    }

    private boolean pricesNegative(SearchProductByOtherRequest searchProductRequest) {
        return searchProductRequest.getStartPrice() < 0
                || searchProductRequest.getEndPrice() < 0;
    }

    private boolean fieldsFilledCorrect(SearchProductByOtherRequest searchProductRequest) {
        return (searchProductRequest.getStartPrice() != null && searchProductRequest.getEndPrice() != null)
                || (searchProductRequest.getStartPrice() == null && searchProductRequest.getEndPrice() == null);
    }


    private boolean isNotValidInputForOrdering(SearchProductByOtherRequest searchProductRequest){
        return (!searchProductRequest.getOrdering().filledBoth() &&
                !searchProductRequest.getOrdering().emptyBothFields()) ||
                (isNotValidInputForOrderBy(searchProductRequest)) ||
                (isNotValidInputForOrderDirection(searchProductRequest));
    }

    private boolean isNotValidInputForOrderBy(SearchProductByOtherRequest searchProductRequest){
        return !searchProductRequest.getOrdering().getOrderBy().equals("title") &&
                !searchProductRequest.getOrdering().getOrderBy().equals("description") &&
                !searchProductRequest.getOrdering().getOrderBy().equals("price");
    }

    private boolean isNotValidInputForOrderDirection(SearchProductByOtherRequest searchProductRequest){
        return !searchProductRequest.getOrdering().getOrderDirection().equals("ASC") &&
                !searchProductRequest.getOrdering().getOrderDirection().equals("DESC");
    }

    private List<CoreError> updateErrorsListForOrdering(SearchProductByOtherRequest searchProductRequest){

        List<CoreError>errors = new ArrayList<>();

        if (!searchProductRequest.getOrdering().filledBoth() &&
                !searchProductRequest.getOrdering().emptyBothFields()) {
            errors.add(new CoreError("search", "Not valid input for ordering parameters"));
            return errors;
        }
        if (isNotValidInputForOrderBy(searchProductRequest) && searchProductRequest.getOrdering().filledBoth()) {
            errors.add(new CoreError("orderBy", "Not valid input for orderBy"));
        }
        if (isNotValidInputForOrderDirection(searchProductRequest) && searchProductRequest.getOrdering().filledBoth()) {
            errors.add(new CoreError("orderDirection", "Not valid input for orderDirection"));
        }
        return  errors;
    }

    private boolean isNotValidRequestForPaging (SearchProductByOtherRequest searchProductRequest){
        return (!searchProductRequest.getPaging().isFilledBoth() &&
                !searchProductRequest.getPaging().isEmptyBoth()) ||
                (isNotValidInputForPageNumber(searchProductRequest)) ||
                isNotValidInputForPageSize(searchProductRequest);
    }

    private boolean isNotValidInputForPageNumber(SearchProductByOtherRequest searchProductRequest){
        return searchProductRequest.getPaging().getPageNumber() <= 0;
    }

    private boolean isNotValidInputForPageSize(SearchProductByOtherRequest searchProductRequest){
        return searchProductRequest.getPaging().getPageSize() <= 0;
    }

    private List<CoreError> updateErrorsListForPaging(SearchProductByOtherRequest searchProductRequest){

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
