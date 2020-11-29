package application_target_list.core.validators;

import application_target_list.core.requests.SearchTargetByDescriptionRequest;
import application_target_list.core.responses.CoreError;
import java.util.ArrayList;
import java.util.List;

public class SearchTargetByDescriptionValidator {

    public List<CoreError> validate(SearchTargetByDescriptionRequest request){
        List<CoreError> errors = new ArrayList<>();

        if (isTargetNameEmpty(request)) {
            errors.add(new CoreError("Target description", "must not be empty!"));
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


        if (request.getPaging() != null){
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

    private boolean isOrdering(SearchTargetByDescriptionRequest request){
        return request.getOrdering() != null;
    }

    private boolean isOrderDirectionIncorrect(SearchTargetByDescriptionRequest request){
        return !request.getOrdering().getOrderDirection().equals("ASCENDING") &&
                !request.getOrdering().getOrderDirection().equals("DESCENDING");
    }

    private boolean isOrderDirectionEmpty(SearchTargetByDescriptionRequest request){
        return request.getOrdering().getOrderDirection().isEmpty() || request.getOrdering().getOrderDirection() == null;
    }

    private boolean isOrderByIncorrect(SearchTargetByDescriptionRequest request){
        return !request.getOrdering().getOrderBy().equals("name") && !request.getOrdering().getOrderBy().equals("description") &&
                !request.getOrdering().getOrderBy().equals("deadline");
    }

    private boolean isOrderByEmpty(SearchTargetByDescriptionRequest request){
        return request.getOrdering().getOrderBy().isEmpty() || request.getOrdering().getOrderBy() == null;
    }

    private boolean isPageNumberCorrect(SearchTargetByDescriptionRequest request){
        return request.getPaging().getPageNumber() > 0;
    }

    private boolean isPageSizeCorrect(SearchTargetByDescriptionRequest request){
        return request.getPaging().getPageSize() > 0;
    }

    private boolean isPageSizeEmpty(SearchTargetByDescriptionRequest request){
        return request.getPaging().getPageSize() == null;
    }

    private boolean isPageNumberEmpty(SearchTargetByDescriptionRequest request){
        return request.getPaging().getPageNumber() == null;
    }

    private boolean isTargetNameEmpty(SearchTargetByDescriptionRequest request) {
        return request.getDescription() == null || request.getDescription().isEmpty();
    }
}
