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
