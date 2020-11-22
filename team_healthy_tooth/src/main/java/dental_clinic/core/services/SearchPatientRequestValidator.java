package dental_clinic.core.services;

import dental_clinic.core.requests.SearchPatientRequest;
import dental_clinic.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class SearchPatientRequestValidator {

    public List<CoreError> validate (SearchPatientRequest searchPatientRequest) {
        List<CoreError> errors = new ArrayList<>();

        if (searchRequestIsEmpty(searchPatientRequest)) {
            errors.add(new CoreError("search", "Not valid input for search"));
        }
        if (isNotValidInputForOrdering(searchPatientRequest)){
            errors.addAll(updateErrorsListForOrdering(searchPatientRequest));
        }
        if (isNotValidInputForPaging(searchPatientRequest)){
            errors.addAll(updateErrorsListForPaging(searchPatientRequest));
        }
        return errors;
    }

    private boolean searchRequestIsEmpty(SearchPatientRequest searchPatientRequest){
        return (searchPatientRequest.getName() == null || searchPatientRequest.getName().isEmpty()) &&
                (searchPatientRequest.getSurname() == null || searchPatientRequest.getSurname().isEmpty());
    }

    private boolean isNotValidInputForOrdering(SearchPatientRequest searchPatientRequest){
        return (!searchPatientRequest.getOrdering().filledBoth() &&
                !searchPatientRequest.getOrdering().emptyBothFields()) ||
                (isNotValidInputForOrderBy(searchPatientRequest)) ||
                (isNotValidInputForOrderDirection(searchPatientRequest));
    }

    private boolean isNotValidInputForPaging(SearchPatientRequest searchPatientRequest){
        return (!searchPatientRequest.getPaging().isFilledBoth() &&
                !searchPatientRequest.getPaging().isEmptyBoth()) ||
                (isNotValidInputForPageNumber(searchPatientRequest)) ||
                isNotValidInputForPageSize(searchPatientRequest);
    }

    private boolean isNotValidInputForOrderBy(SearchPatientRequest searchPatientRequest){
        return !searchPatientRequest.getOrdering().getOrderBy().equals("name") &&
                !searchPatientRequest.getOrdering().getOrderBy().equals("surname");
    }

    private boolean isNotValidInputForOrderDirection(SearchPatientRequest searchPatientRequest){
        return !searchPatientRequest.getOrdering().getOrderDirection().equals("ASC") &&
                !searchPatientRequest.getOrdering().getOrderDirection().equals("DESC");
    }

    private boolean isNotValidInputForPageNumber(SearchPatientRequest searchPatientRequest){
        return searchPatientRequest.getPaging().getPageNumber() <= 0;
    }

    private boolean isNotValidInputForPageSize(SearchPatientRequest searchPatientRequest){
        return searchPatientRequest.getPaging().getPageSize() <= 0;
    }

    private List<CoreError> updateErrorsListForOrdering(SearchPatientRequest searchPatientRequest){

        List<CoreError>errors = new ArrayList<>();

        if (!searchPatientRequest.getOrdering().filledBoth() &&
                !searchPatientRequest.getOrdering().emptyBothFields()) {
            errors.add(new CoreError("search", "Not valid input for ordering parameters"));
        }else {
            if (isNotValidInputForOrderBy(searchPatientRequest) && searchPatientRequest.getOrdering().filledBoth()) {
                errors.add(new CoreError("orderBy", "Not valid input for orderBy"));
            }
            if (isNotValidInputForOrderDirection(searchPatientRequest) && searchPatientRequest.getOrdering().filledBoth()) {
                errors.add(new CoreError("orderDirection", "Not valid input for orderDirection"));
            }
        }
        return  errors;
    }

    private List<CoreError> updateErrorsListForPaging(SearchPatientRequest searchPatientRequest){

        List<CoreError>errors = new ArrayList<>();

        if (!searchPatientRequest.getPaging().isFilledBoth() &&
                !searchPatientRequest.getPaging().isEmptyBoth()) {
            errors.add(new CoreError("search", "Not valid input for paging parameters"));
        }else {
            if (isNotValidInputForPageNumber(searchPatientRequest)) {
                errors.add(new CoreError("pageNumber", "Not valid input for page number"));
            }
            if (isNotValidInputForPageSize(searchPatientRequest)) {
                errors.add(new CoreError("pageSize", "Not valid input for page size"));
            }
        }
        return  errors;
    }

}
