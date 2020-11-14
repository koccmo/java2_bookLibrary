package dental_clinic.core.services;

import dental_clinic.core.requests.FindPatientBySurnameRequest;
import dental_clinic.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FindPatientsBySurnameValidatorTest {

    FindPatientsBySurnameValidator findPatientsBySurnameValidator = new FindPatientsBySurnameValidator();

    @Test
    public void testSurnameIsEmpty(){
        CoreError expectedCoreError = new CoreError("surname", "Not valid input for surname");

        FindPatientBySurnameRequest findPatientBySurnameRequest = new FindPatientBySurnameRequest("");
        List<CoreError> errors = findPatientsBySurnameValidator.validate(findPatientBySurnameRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedCoreError));
    }

    @Test
    public void testValidInput(){

        FindPatientBySurnameRequest findPatientBySurnameRequest = new FindPatientBySurnameRequest("Surname");
        List<CoreError> errors = findPatientsBySurnameValidator.validate(findPatientBySurnameRequest);

        assertTrue(errors.isEmpty());
    }

}