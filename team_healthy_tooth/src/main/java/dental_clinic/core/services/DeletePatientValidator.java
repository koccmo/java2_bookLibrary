package dental_clinic.core.services;

import dental_clinic.core.requests.DeletePatientRequest;
import dental_clinic.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class DeletePatientValidator {

    public List<CoreError> validate (DeletePatientRequest deletePatientRequest){
        List <CoreError> errors = new ArrayList<>();

        long id = deletePatientRequest.getId();
        if (id < 1 ) {
            errors.add(new CoreError("id", "Not valid input for id"));
        }

        return errors;
    }
}