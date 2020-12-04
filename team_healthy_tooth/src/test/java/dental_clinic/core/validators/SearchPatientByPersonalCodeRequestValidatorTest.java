package dental_clinic.core.validators;

import dental_clinic.core.requests.SearchPatientByPersonalCodeRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.services.validators.SearchPatientByPersonalCodeRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchPatientByPersonalCodeRequestValidatorTest {

    SearchPatientByPersonalCodeRequestValidator searchPatientByPersonalCodeRequestValidator = new SearchPatientByPersonalCodeRequestValidator();

    @Test
    public void testCodeContainsLetters(){
        CoreError expectedError = new CoreError("Personal data : personal code",
                    "Invalid input for personal code!");

        SearchPatientByPersonalCodeRequest searchPatientByPersonalCodeRequest = new SearchPatientByPersonalCodeRequest("lklkmn-oipl");
        List<CoreError> errors = searchPatientByPersonalCodeRequestValidator.validate(searchPatientByPersonalCodeRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));

    }

    @Test
    public void testNotValidLength(){
        CoreError expectedError = new CoreError("Personal data : personal code",
                "Invalid input for personal code!");

        SearchPatientByPersonalCodeRequest searchPatientByPersonalCodeRequest = new SearchPatientByPersonalCodeRequest("121212123");
        List<CoreError> errors = searchPatientByPersonalCodeRequestValidator.validate(searchPatientByPersonalCodeRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidInputWithDefis(){

        SearchPatientByPersonalCodeRequest searchPatientByPersonalCodeRequest = new SearchPatientByPersonalCodeRequest("150385-12223");
        List<CoreError> errors = searchPatientByPersonalCodeRequestValidator.validate(searchPatientByPersonalCodeRequest);

        assertTrue(errors.size() == 0);
    }

    @Test
    public void testValidInputWithoutDefis(){

        SearchPatientByPersonalCodeRequest searchPatientByPersonalCodeRequest = new SearchPatientByPersonalCodeRequest("25047512569");
        List<CoreError> errors = searchPatientByPersonalCodeRequestValidator.validate(searchPatientByPersonalCodeRequest);

        assertTrue(errors.size() == 0);
    }

}