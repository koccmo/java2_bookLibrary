package dental_clinic.core.services.validators;

import dental_clinic.core.requests.GetSpecificPatientHistoryRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;

@DIComponent
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
