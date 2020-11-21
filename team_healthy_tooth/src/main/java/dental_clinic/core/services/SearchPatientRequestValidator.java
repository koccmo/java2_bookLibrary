package dental_clinic.core.services;

import dental_clinic.core.requests.SearchPatientRequest;
import dental_clinic.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class SearchPatientRequestValidator {

    public List<CoreError> validate (SearchPatientRequest searchPatientRequest) {

        List<CoreError> errors = new ArrayList<>();

        if ((searchPatientRequest.getName() == null || searchPatientRequest.getName().isEmpty()) &&
                (searchPatientRequest.getSurname() == null || searchPatientRequest.getSurname().isEmpty())) {
            errors.add(new CoreError("search", "Not valid input for search"));
        }

        return errors;
    }

}
