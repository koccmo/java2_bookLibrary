package dental_clinic.core.services.validators;

import dental_clinic.core.requests.GetAllPatientsRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class GetAllPatientsRequestValidator {

    public List<CoreError> validate (GetAllPatientsRequest getAllPatientsRequest){

        return new ArrayList<>();
    }
}
