package dental_clinic.core.services;

import dental_clinic.core.requests.FindPatientBySurnameRequest;
import dental_clinic.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class FindPatientsBySurnameValidator {

    public List<CoreError> validate(FindPatientBySurnameRequest findPatientBySurnameRequest){
        List<CoreError> errors = new ArrayList<>();

        String surname = findPatientBySurnameRequest.getSurname();

        if (surname == null || surname.isEmpty()){
            errors.add(new CoreError("surname", "Not valid input for surname"));
        }

        return errors;
    }
}
