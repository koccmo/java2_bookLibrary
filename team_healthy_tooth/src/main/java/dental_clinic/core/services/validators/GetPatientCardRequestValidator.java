package dental_clinic.core.services.validators;

import dental_clinic.core.requests.GetPatientCardRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class GetPatientCardRequestValidator {

    public List<CoreError> validate (GetPatientCardRequest getPatientCardRequest){

        List <CoreError> errors = new ArrayList<>();

        if ((getPatientCardRequest.getId() == null) || (getPatientCardRequest.getId() < 1)){
            errors.add(new CoreError("id", "Not valid input for id"));
        }

        return errors;
    }
}
