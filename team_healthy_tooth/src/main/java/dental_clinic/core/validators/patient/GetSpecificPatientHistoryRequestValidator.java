package dental_clinic.core.validators.patient;

import dental_clinic.core.requests.patient.GetSpecificPatientHistoryRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetSpecificPatientHistoryRequestValidator {

    public List<CoreError> validate (GetSpecificPatientHistoryRequest getSpecificPatientHistoryRequest){

        List <CoreError> errors = new ArrayList<>();

        Long id = getSpecificPatientHistoryRequest.getId();

        if ((id == null) || (id < 1)){
            errors.add(new CoreError("id", "Not valid input for id"));
        }

        return errors;
    }
}
