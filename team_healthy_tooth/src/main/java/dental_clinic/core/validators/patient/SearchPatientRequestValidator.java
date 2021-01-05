package dental_clinic.core.validators.patient;

import dental_clinic.core.domain.OrderingDirection;
import dental_clinic.core.requests.patient.SearchPatientRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchPatientRequestValidator {

    public List<CoreError> validate (SearchPatientRequest searchPatientRequest) {
        List<CoreError> errors = new ArrayList<>();

        errors.addAll(searchRequestIsEmptyError(searchPatientRequest));
        errors.addAll(searchRequestIsNotValidErrors(searchPatientRequest));

        if (isNotValidInputForOrdering(searchPatientRequest)){
            errors.addAll(updateErrorsListForOrdering(searchPatientRequest));
        }
        if (isNotValidRequestForPaging(searchPatientRequest)){
            errors.addAll(updateErrorsListForPaging(searchPatientRequest));
        }
        return errors;
    }

    private List<CoreError> searchRequestIsEmptyError(SearchPatientRequest searchPatientRequest){
        List<CoreError> errors = new ArrayList<>();
        if ((searchPatientRequest.getName() == null || searchPatientRequest.getName().isEmpty()) &&
                (searchPatientRequest.getSurname() == null || searchPatientRequest.getSurname().isEmpty())) {
            errors.add(new CoreError("search", "Search request can't be empty"));
        }
        return  errors;
    }

    private List<CoreError> searchRequestIsNotValidErrors(SearchPatientRequest searchPatientRequest) {
        List<CoreError> errors = new ArrayList<>();
        if (searchPatientRequest.getName() != null && !searchPatientRequest.getName().isEmpty()) {
            if (!searchPatientRequest.getName().matches("[a-zA-ZēūīōāšģķļžčņĒŪĪŌĀŠĢĶĻŽČŅ]+")) {
                errors.add(new CoreError("name", "Name can contain only letters"));
            }
        }
        if (searchPatientRequest.getSurname() != null && !searchPatientRequest.getSurname().isEmpty()) {
            if (!searchPatientRequest.getSurname().matches("[a-zA-ZēūīōāšģķļžčņĒŪĪŌĀŠĢĶĻŽČŅ]+")) {
                errors.add(new CoreError("surname", "Surname can contain only letters"));
            }
        }
        return errors;
    }

    private boolean isNotValidInputForOrdering(SearchPatientRequest searchPatientRequest){
        return (!searchPatientRequest.getOrdering().filledBoth() &&
                !searchPatientRequest.getOrdering().emptyBothFields()) ||
                (isNotValidInputForOrderBy(searchPatientRequest)) ||
                (isNotValidInputForOrderDirection(searchPatientRequest));
    }

    private boolean isNotValidRequestForPaging(SearchPatientRequest searchPatientRequest){
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
        return searchPatientRequest.getOrdering().getOrderDirection().equals(OrderingDirection.NOT_VALID);
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
