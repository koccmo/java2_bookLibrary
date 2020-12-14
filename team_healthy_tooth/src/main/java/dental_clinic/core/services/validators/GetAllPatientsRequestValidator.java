package dental_clinic.core.services.validators;

import dental_clinic.core.requests.GetAllPatientsRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllPatientsRequestValidator {

    public List<CoreError> validate (GetAllPatientsRequest getAllPatientsRequest){

        return new ArrayList<>();
    }
}
