package application_target_list.core.validators;


import application_target_list.core.requests.SearchTargetByNameRequest;
import application_target_list.core.responses.CoreError;
import application_target_list.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class SearchTargetByNameValidator {

    public List<CoreError> validate(SearchTargetByNameRequest request){
        List<CoreError> errors = new ArrayList<>();

        if (isTargetNameEmpty(request)) {
            errors.add(new CoreError("Target name", "must not be empty!"));
        }

        if (isOrdering(request)){
            if (isOrderByEmpty(request)){
                errors.add(new CoreError("Order by", "must not be empty"));
            }
            if (isOrderByIncorrect(request)){
                errors.add(new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!"));
            }
            if (isOrderDirectionEmpty(request)){
                errors.add(new CoreError("Order direction", "must not be empty"));
            }
            if (isOrderDirectionIncorrect(request)){
                errors.add(new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!"));
            }
        }

        if (isPaging(request)){
            if (!isPageNumberCorrect(request)) {
                errors.add(new CoreError("Page number", "must be greater then 0!"));
            }
            if (isPageNumberEmpty(request)){
                errors.add(new CoreError("Page number", "must not be empty"));
            }
            if (!isPageSizeCorrect(request)){
                errors.add(new CoreError("Page size", "must be greater then 0!"));
            }
            if (isPageSizeEmpty(request)){
                errors.add(new CoreError("Page size", "must not be empty"));
            }
        }
        return errors;
    }

    private boolean isOrderDirectionIncorrect(SearchTargetByNameRequest request){
        return !request.getOrdering().getOrderDirection().equals("ASCENDING") &&
                !request.getOrdering().getOrderDirection().equals("DESCENDING");
    }

    private boolean isOrderDirectionEmpty(SearchTargetByNameRequest request){
        return request.getOrdering().getOrderDirection().isEmpty() || request.getOrdering().getOrderDirection() == null;
    }

    private boolean isOrderByIncorrect(SearchTargetByNameRequest request){
        return !request.getOrdering().getOrderBy().equals("name") && !request.getOrdering().getOrderBy().equals("description") &&
                !request.getOrdering().getOrderBy().equals("deadline");
    }

    private boolean isOrderByEmpty(SearchTargetByNameRequest request){
        return request.getOrdering().getOrderBy().isEmpty() || request.getOrdering().getOrderBy() == null;
    }

    private boolean isPageNumberEmpty(SearchTargetByNameRequest request){
        return request.getPaging().getPageNumber() == null;
    }

    private boolean isPageSizeEmpty(SearchTargetByNameRequest request){
        return request.getPaging().getPageSize() == null;
    }

    private boolean isPageNumberCorrect(SearchTargetByNameRequest request){
        return request.getPaging().getPageNumber() > 0;
    }

    private boolean isPageSizeCorrect(SearchTargetByNameRequest request){
        return request.getPaging().getPageSize() > 0;
    }

    private boolean isPaging(SearchTargetByNameRequest request){
        return request.getPaging() != null;
    }

    private boolean isOrdering(SearchTargetByNameRequest request){
        return request.getOrdering() != null;
    }

    private boolean isTargetNameEmpty(SearchTargetByNameRequest request) {
        return request.getName() == null || request.getName().isEmpty();
    }



}
