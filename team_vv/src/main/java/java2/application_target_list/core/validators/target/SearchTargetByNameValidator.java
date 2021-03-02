package java2.application_target_list.core.validators.target;

import java2.application_target_list.core.requests.target.SearchTargetByNameRequest;
import org.springframework.stereotype.Component;
import java2.application_target_list.core.responses.CoreError;
import java.util.ArrayList;
import java.util.List;

@Component
public class SearchTargetByNameValidator {

    private List<CoreError> errors;

    public List<CoreError> validate(SearchTargetByNameRequest searchTargetByNameRequest){
        errors = new ArrayList<>();
        checkTargetName(searchTargetByNameRequest);
        checkOrdering(searchTargetByNameRequest);
        checkPaging(searchTargetByNameRequest);
        return errors;
    }

    private void checkOrdering(SearchTargetByNameRequest searchTargetByNameRequest){
        if (isOrdering(searchTargetByNameRequest)){
            if (isOrderByEmpty(searchTargetByNameRequest)){
                errors.add(createOrderByIsEmptyError());
            }
            if (isOrderByIncorrect(searchTargetByNameRequest)){
                errors.add(createOrderByIsIncorrectError());
            }
            if (isOrderDirectionEmpty(searchTargetByNameRequest)){
                errors.add(createOrderDirectionIsEmptyError());
            }
            if (isOrderDirectionIncorrect(searchTargetByNameRequest)){
                errors.add(createIncorrectOrderDirectionError());
            }
        }
    }

    private void checkPaging(SearchTargetByNameRequest searchTargetByNameRequest){
        if (isPaging(searchTargetByNameRequest)){
            if (!isPageNumberCorrect(searchTargetByNameRequest)) {
                errors.add(createIncorrectPageNumberError());
            }

            if (isPageNumberEmpty(searchTargetByNameRequest)){
                errors.add(createPageNumberIsEmptyError());
            }

            if (!isPageSizeCorrect(searchTargetByNameRequest)){
                errors.add(createIncorrectPageSizeError());
            }

            if (isPageSizeEmpty(searchTargetByNameRequest)){
                errors.add(createPageSizeIsEmptyError());
            }
        }
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
        return new CoreError("Order by", "must contain NAME, DESCRIPTION or DEADLINE only!");
    }

    private CoreError createOrderByIsEmptyError(){
        return new CoreError("Order by", "must not be empty");
    }

    private void checkTargetName(SearchTargetByNameRequest searchTargetByNameRequest){
        if (isTargetNameEmpty(searchTargetByNameRequest)) {
            errors.add(createTargetNameIsEmptyError());
        }
    }

    private CoreError createTargetNameIsEmptyError(){
        return new CoreError("Target name", "must not be empty!");
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
