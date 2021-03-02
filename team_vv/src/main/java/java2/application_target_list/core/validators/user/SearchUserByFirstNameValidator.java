package java2.application_target_list.core.validators.user;

import java2.application_target_list.core.requests.user.SearchUsersByFirstNameRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchUserByFirstNameValidator {

    private List<CoreError> errors;

    public List<CoreError> validate(SearchUsersByFirstNameRequest searchUsersByFirstNameRequest){
        errors = new ArrayList<>();
        checkUserFirstName(searchUsersByFirstNameRequest);
        checkOrdering(searchUsersByFirstNameRequest);
        checkPaging(searchUsersByFirstNameRequest);
        return errors;
    }

    private void checkUserFirstName(SearchUsersByFirstNameRequest searchUsersByFirstNameRequest){
        if (isUserFirstNameEmpty(searchUsersByFirstNameRequest)) {
            errors.add(createUserFirstNameIsEmptyError());
        }
    }

    private void checkOrdering(SearchUsersByFirstNameRequest searchUsersByFirstNameRequest){
        if (isOrdering(searchUsersByFirstNameRequest)){
            if (isOrderByEmpty(searchUsersByFirstNameRequest)){
                errors.add(createOrderByIsEmptyError());
            }
            if (isOrderByIncorrect(searchUsersByFirstNameRequest)){
                errors.add(createOrderByIsIncorrectError());
            }
            if (isOrderDirectionEmpty(searchUsersByFirstNameRequest)){
                errors.add(createOrderDirectionIsEmptyError());
            }
            if (isOrderDirectionIncorrect(searchUsersByFirstNameRequest)){
                errors.add(createIncorrectOrderDirectionError());
            }
        }
    }

    private void checkPaging(SearchUsersByFirstNameRequest searchUsersByFirstNameRequest){
        if (isPaging(searchUsersByFirstNameRequest)){
            if (!isPageNumberCorrect(searchUsersByFirstNameRequest)) {
                errors.add(createIncorrectPageNumberError());
            }

            if (isPageNumberEmpty(searchUsersByFirstNameRequest)){
                errors.add(createPageNumberIsEmptyError());
            }

            if (!isPageSizeCorrect(searchUsersByFirstNameRequest)){
                errors.add(createIncorrectPageSizeError());
            }

            if (isPageSizeEmpty(searchUsersByFirstNameRequest)){
                errors.add(createPageSizeIsEmptyError());
            }
        }
    }

    private CoreError createUserFirstNameIsEmptyError(){
        return new CoreError("User first name", "must not be empty!");
    }

    private CoreError createPageSizeIsEmptyError() {
        return new CoreError("Page size", "must not be empty");
    }

    private CoreError createIncorrectPageSizeError(){
        return new CoreError("Page size", "must be greater then 0!");
    }

    private CoreError createPageNumberIsEmptyError(){
        return new CoreError("Page number", "must not be empty");
    }

    private CoreError createIncorrectPageNumberError(){
        return new CoreError("Page number", "must be greater then 0!");
    }

    private CoreError createIncorrectOrderDirectionError(){
        return new CoreError("Order direction", "must contain ASCENDING or DESCENDING only!");
    }

    private CoreError createOrderDirectionIsEmptyError(){
        return new CoreError("Order direction", "must not be empty");
    }

    private CoreError createOrderByIsIncorrectError(){
        return new CoreError("Order by", "must contain FIRST NAME or LAST NAME only!");
    }

    private CoreError createOrderByIsEmptyError(){
        return new CoreError("Order by", "must not be empty");
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
