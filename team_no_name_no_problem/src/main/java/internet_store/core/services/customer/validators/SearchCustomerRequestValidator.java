package internet_store.core.services.customer.validators;


import internet_store.core.requests.customer.SearchCustomerRequest;
import internet_store.core.response.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component public class SearchCustomerRequestValidator {

    public List<CoreError> validate(SearchCustomerRequest searchCustomerRequest){

        List<CoreError> errors = new ArrayList<>();

        if (isNameAndSurnameEmpty(searchCustomerRequest.getName(), searchCustomerRequest.getSurname())){
            errors.add(new CoreError("search", "Not valid input for search"));
        }

        if (!containsOnlyLetters(searchCustomerRequest.getName())){
            errors.add(new CoreError("name", "Not valid input for name, should contain only letters"));
        }

        if (!containsOnlyLetters(searchCustomerRequest.getSurname())){
            errors.add(new CoreError("surname", "Not valid input for surname, should contain only letters"));
        }

        if (isNotValidInputForOrdering(searchCustomerRequest)){
            errors.addAll(updateErrorsListForOrdering(searchCustomerRequest));
        }

        if (isNotValidRequestForPaging(searchCustomerRequest)){
            errors.addAll(updateErrorsListForPaging(searchCustomerRequest));
        }
        return errors;
    }

    private boolean isNameAndSurnameEmpty(String name, String surname){
        return (name == null || name.isEmpty()) && (surname == null || surname.isEmpty());
    }

    private boolean numberContainsOnlyDigits(String input){
        String regex = "[0-9]+";

        Pattern pattern = Pattern.compile(regex);
        if (input == null){
            return false;
        }
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    private boolean containsOnlyLetters(String input){
        String regex = "[a-zA-Z]+";

        Pattern pattern = Pattern.compile(regex);
        if (input == null){
            return false;
        }
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    private boolean isNotValidInputForOrdering(SearchCustomerRequest searchCustomerRequest){
        return (!searchCustomerRequest.getOrdering().filledBoth() &&
                !searchCustomerRequest.getOrdering().emptyBothFields()) ||
                (isNotValidInputForOrderBy(searchCustomerRequest)) ||
                (isNotValidIntputForOrderDirection(searchCustomerRequest));
    }

    private boolean isNotValidInputForOrderBy(SearchCustomerRequest searchCustomerRequest){
        return !searchCustomerRequest.getOrdering().getOrderBy().equals("name") &&
                !searchCustomerRequest.getOrdering().getOrderBy().equals("surname");
    }

    private boolean isNotValidIntputForOrderDirection(SearchCustomerRequest searchCustomerRequest){
        return !searchCustomerRequest.getOrdering().getOrderDirection().equals("ASC") &&
                !searchCustomerRequest.getOrdering().getOrderDirection().equals("DESC");
    }

    private List<CoreError> updateErrorsListForOrdering(SearchCustomerRequest searchCustomerRequest){

        List<CoreError> errors = new ArrayList<>();

        if (!searchCustomerRequest.getOrdering().emptyBothFields()) {
            errors.add(new CoreError("search", "Not valid input for ordering parameters"));
        } else {
            if (isNotValidInputForOrderBy(searchCustomerRequest) && searchCustomerRequest.getOrdering()
            .filledBoth()){
                errors.add(new CoreError("orderBy", "Not valid input of orderBy"));
            }
            if (isNotValidIntputForOrderDirection(searchCustomerRequest) && searchCustomerRequest.getOrdering()
            .filledBoth()){
                errors.add(new CoreError("orderDirection", "Not valid input for orderDirection"));
            }
        }
        return errors;
    }

    private boolean isNotValidRequestForPaging(SearchCustomerRequest searchCustomerRequest){
        return (!searchCustomerRequest.getPaging().isFilledBoth() &&
                !searchCustomerRequest.getPaging().isEmptyBoth()) ||
                (isNotValidInputForPageNumber(searchCustomerRequest)) ||
                isNotValidInputForPageSize(searchCustomerRequest);
    }

    private boolean isNotValidInputForPageNumber(SearchCustomerRequest searchCustomerRequest){
        return searchCustomerRequest.getPaging().getPageNumber() <= 0;
    }

    private boolean isNotValidInputForPageSize(SearchCustomerRequest searchCustomerRequest){
        return searchCustomerRequest.getPaging().getPageSize() <= 0;
    }

    private List<CoreError> updateErrorsListForPaging(SearchCustomerRequest searchCustomerRequest){

        List<CoreError> errors = new ArrayList<>();

        if (!searchCustomerRequest.getPaging().isFilledBoth() &&
            !searchCustomerRequest.getPaging().isEmptyBoth()){
            errors.add(new CoreError("search", "Not valid input for paging parameters"));
        } else {
            if (isNotValidInputForPageNumber(searchCustomerRequest)){
                errors.add(new CoreError("pageNumber", "Not valid input for page number"));
            }
            if (isNotValidInputForPageSize(searchCustomerRequest)){
                errors.add(new CoreError("pageSize", "Not valid input for page size"));
            }
        }
        return errors;
    }
}
