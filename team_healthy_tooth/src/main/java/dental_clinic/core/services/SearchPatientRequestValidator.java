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
        }   if (isNotValidInputForOrdering(searchPatientRequest)){
                errors.add(new CoreError("search", "Not valid input for ordering parameters"));
            }else{
            if (isNotValidInputForOrderBy(searchPatientRequest) && searchPatientRequest.getOrdering().filledBoth()){
                errors.add(new CoreError("orderBy", "Not valid input for orderBy"));
            }
            if (isNotValidInputForOrderDirection(searchPatientRequest) && searchPatientRequest.getOrdering().filledBoth()){
                errors.add(new CoreError("orderDirection", "Not valid input for orderDirection"));
            }
        }
        return errors;
    }

    private boolean searchRequestIsEmpty(SearchPatientRequest searchPatientRequest){
        return (searchPatientRequest.getName() == null || searchPatientRequest.getName().isEmpty()) &&
                (searchPatientRequest.getSurname() == null || searchPatientRequest.getSurname().isEmpty());
    }

    private boolean isNotValidInputForOrdering(SearchPatientRequest searchPatientRequest){
        return !searchPatientRequest.getOrdering().filledBoth() &&
                !searchPatientRequest.getOrdering().emptyBothFields();
    }

    private boolean isNotValidInputForOrderBy(SearchPatientRequest searchPatientRequest){
        return !searchPatientRequest.getOrdering().getOrderBy().equals("name") &&
                !searchPatientRequest.getOrdering().getOrderBy().equals("surname");
    }

    private boolean isNotValidInputForOrderDirection(SearchPatientRequest searchPatientRequest){
        return !searchPatientRequest.getOrdering().getOrderDirection().equals("ASC") &&
                !searchPatientRequest.getOrdering().getOrderDirection().equals("DESC");
    }

}
