package application_target_list.core.validators;


import application_target_list.core.requests.SearchTargetByNameRequest;
import application_target_list.core.responses.CoreError;
import java.util.ArrayList;
import java.util.List;

public class SearchTargetByNameValidator {

    public List<CoreError> validate(SearchTargetByNameRequest request){
        List<CoreError> errors = new ArrayList<>();

        if (isTargetNameEmpty(request)) {
            errors.add(new CoreError("Target name", "must not be empty!"));
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

    private boolean isTargetNameEmpty(SearchTargetByNameRequest request) {
        return request.getName() == null || request.getName().isEmpty();
    }



}
