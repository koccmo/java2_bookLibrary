package dental_clinic.core.services;

import dental_clinic.core.requests.FindPatientByPersonalCodeRequest;
import dental_clinic.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FindPatientByPersonalCodeValidatorTest {

    FindPatientByPersonalCodeValidator findPatientByPersonalCodeValidator = new FindPatientByPersonalCodeValidator();

    @Test
    public void testCodeContainsLetters(){
        CoreError expectedError = new CoreError("Personal data : personal code",
                    "Invalid input for personal code!");

        FindPatientByPersonalCodeRequest findPatientByPersonalCodeRequest = new FindPatientByPersonalCodeRequest("lklkmn-oipl");
        List<CoreError> errors = findPatientByPersonalCodeValidator.validate(findPatientByPersonalCodeRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));

    }

    @Test
    public void testNotValidLength(){
        CoreError expectedError = new CoreError("Personal data : personal code",
                "Invalid input for personal code!");

        FindPatientByPersonalCodeRequest findPatientByPersonalCodeRequest = new FindPatientByPersonalCodeRequest("121212123");
        List<CoreError> errors = findPatientByPersonalCodeValidator.validate(findPatientByPersonalCodeRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidInputWithDefis(){

        FindPatientByPersonalCodeRequest findPatientByPersonalCodeRequest = new FindPatientByPersonalCodeRequest("150385-12223");
        List<CoreError> errors = findPatientByPersonalCodeValidator.validate(findPatientByPersonalCodeRequest);

        assertTrue(errors.size() == 0);
    }

    @Test
    public void testValidInputWithoutDefis(){

        FindPatientByPersonalCodeRequest findPatientByPersonalCodeRequest = new FindPatientByPersonalCodeRequest("25047512569");
        List<CoreError> errors = findPatientByPersonalCodeValidator.validate(findPatientByPersonalCodeRequest);

        assertTrue(errors.size() == 0);
    }

}