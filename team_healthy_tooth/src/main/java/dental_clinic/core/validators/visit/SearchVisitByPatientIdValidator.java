package dental_clinic.core.validators.visit;

import dental_clinic.core.requests.visit.SearchVisitByPatientIdRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchVisitByPatientIdValidator {

    public List<CoreError> validate (SearchVisitByPatientIdRequest searchVisitByPatientIdRequest) {

        List<CoreError>errors = new ArrayList<>();

        if (searchVisitByPatientIdRequest.getId() == null || searchVisitByPatientIdRequest.getId() < 1L) {
            errors.add((new CoreError("id", "Not valid input for id")));
        }
        return errors;
    }
}
