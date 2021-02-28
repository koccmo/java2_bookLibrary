package java2.application_target_list.core.validators.user;



import java2.application_target_list.core.requests.user.SearchUsersByLastNameRequest;
import java2.application_target_list.core.responses.CoreError;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchUserByLastNameValidator {

    private List<CoreError> errors;

    public List<CoreError> validate(SearchUsersByLastNameRequest searchUsersByLastNameRequest){
        errors = new ArrayList<>();
        checkUserLastName(searchUsersByLastNameRequest);
        checkOrdering(searchUsersByLastNameRequest);
        checkPaging(searchUsersByLastNameRequest);
        return errors;
    }

    private void checkUserLastName(SearchUsersByLastNameRequest searchUsersByLastNameRequest){
        if (isUserLastNameEmpty(searchUsersByLastNameRequest)) {
            errors.add(createUserLastNameIsEmptyError());
        }
    }

    private void checkOrdering(SearchUsersByLastNameRequest searchUsersByLastNameRequest){
        if (isOrdering(searchUsersByLastNameRequest)){
            if (isOrderByEmpty(searchUsersByLastNameRequest)){
                errors.add(createOrderByIsEmptyError());
            }
            if (isOrderByIncorrect(searchUsersByLastNameRequest)){
                errors.add(createOrderByIsIncorrectError());
            }
            if (isOrderDirectionEmpty(searchUsersByLastNameRequest)){
                errors.add(createOrderDirectionIsEmptyError());
            }
            if (isOrderDirectionIncorrect(searchUsersByLastNameRequest)){
                errors.add(createIncorrectOrderDirectionError());
            }
        }
    }

    private void checkPaging(SearchUsersByLastNameRequest searchUsersByLastNameRequest){
        if (isPaging(searchUsersByLastNameRequest)){
            if (!isPageNumberCorrect(searchUsersByLastNameRequest)) {
                errors.add(createIncorrectPageNumberError());
            }

            if (isPageNumberEmpty(searchUsersByLastNameRequest)){
                errors.add(createPageNumberIsEmptyError());
            }

            if (!isPageSizeCorrect(searchUsersByLastNameRequest)){
                errors.add(createIncorrectPageSizeError());
            }

            if (isPageSizeEmpty(searchUsersByLastNameRequest)){
                errors.add(createPageSizeIsEmptyError());
            }
        }
    }

    private CoreError createUserLastNameIsEmptyError(){
        return new CoreError("User last name", "must not be empty!");
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

    private boolean isOrderDirectionIncorrect(SearchUsersByLastNameRequest request){
        return !request.getOrdering().getOrderDirection().equals("ASCENDING") &&
                !request.getOrdering().getOrderDirection().equals("DESCENDING");
    }

    private boolean isOrderDirectionEmpty(SearchUsersByLastNameRequest request){
        return request.getOrdering().getOrderDirection().isEmpty() || request.getOrdering().getOrderDirection() == null;
    }

    private boolean isOrderByIncorrect(SearchUsersByLastNameRequest request){
        return !request.getOrdering().getOrderBy().equals("first name") && !request.getOrdering().getOrderBy().equals("last name");
    }

    private boolean isOrderByEmpty(SearchUsersByLastNameRequest request){
        return request.getOrdering().getOrderBy().isEmpty() || request.getOrdering().getOrderBy() == null;
    }

    private boolean isPageNumberEmpty(SearchUsersByLastNameRequest request){
        return request.getPaging().getPageNumber() == null;
    }

    private boolean isPageSizeEmpty(SearchUsersByLastNameRequest request){
        return request.getPaging().getPageSize() == null;
    }

    private boolean isPageNumberCorrect(SearchUsersByLastNameRequest request){
        return request.getPaging().getPageNumber() > 0;
    }

    private boolean isPageSizeCorrect(SearchUsersByLastNameRequest request){
        return request.getPaging().getPageSize() > 0;
    }

    private boolean isPaging(SearchUsersByLastNameRequest request){
        return request.getPaging() != null;
    }

    private boolean isOrdering(SearchUsersByLastNameRequest request){
        return request.getOrdering() != null;
    }

    private boolean isUserLastNameEmpty(SearchUsersByLastNameRequest request) {
        return request.getLastName() == null || request.getLastName().isEmpty();
    }
}
