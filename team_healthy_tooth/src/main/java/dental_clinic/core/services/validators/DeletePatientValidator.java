package dental_clinic.core.services.validators;

import dental_clinic.core.requests.DeletePatientRequest;
import dental_clinic.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeletePatientValidator {

    public List<CoreError> validate (DeletePatientRequest deletePatientRequest){
        List <CoreError> errors = new ArrayList<>();

        Long id = deletePatientRequest.getId();
        if ((id == null) || (id < 1)) {
            errors.add(new CoreError("id", "Not valid input for id"));
        }

        return errors;
    }
}