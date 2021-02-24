package java2.application_target_list.core.validators.user;

import java2.application_target_list.core.requests.user.SearchUsersByFirstNameRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchUserByFirstNameValidator {

    public List<CoreError> validate(SearchUsersByFirstNameRequest request){
        List<CoreError> errors = new ArrayList<>();

        if (isUserFirstNameEmpty(request)) {
            errors.add(new CoreError("User first name", "must not be empty!"));
        }

        if (isOrdering(request)){
            if (isOrderByEmpty(request)){
                errors.add(new CoreError("Order by", "must not be empty"));
            }
            if (isOrderByIncorrect(request)){
                errors.add(new CoreError("Order by", "must contain FIRST NAME or LAST NAME only!"));
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

    private boolean isOrderDirectionIncorrect(SearchUsersByFirstNameRequest request){
        return !request.getOrdering().getOrderDirection().equals("ASCENDING") &&
                !request.getOrdering().getOrderDirection().equals("DESCENDING");
    }

    private boolean isOrderDirectionEmpty(SearchUsersByFirstNameRequest request){
        return request.getOrdering().getOrderDirection().isEmpty() || request.getOrdering().getOrderDirection() == null;
    }

    private boolean isOrderByIncorrect(SearchUsersByFirstNameRequest request){
        return !request.getOrdering().getOrderBy().equals("first name") && !request.getOrdering().getOrderBy().equals("last name");
    }

    private boolean isOrderByEmpty(SearchUsersByFirstNameRequest request){
        return request.getOrdering().getOrderBy().isEmpty() || request.getOrdering().getOrderBy() == null;
    }

    private boolean isPageNumberEmpty(SearchUsersByFirstNameRequest request){
        return request.getPaging().getPageNumber() == null;
    }

    private boolean isPageSizeEmpty(SearchUsersByFirstNameRequest request){
        return request.getPaging().getPageSize() == null;
    }

    private boolean isPageNumberCorrect(SearchUsersByFirstNameRequest request){
        return request.getPaging().getPageNumber() > 0;
    }

    private boolean isPageSizeCorrect(SearchUsersByFirstNameRequest request){
        return request.getPaging().getPageSize() > 0;
    }

    private boolean isPaging(SearchUsersByFirstNameRequest request){
        return request.getPaging() != null;
    }

    private boolean isOrdering(SearchUsersByFirstNameRequest request){
        return request.getOrdering() != null;
    }

    private boolean isUserFirstNameEmpty(SearchUsersByFirstNameRequest request) {
        return request.getFirstName() == null || request.getFirstName().isEmpty();
    }
}
