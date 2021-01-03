package dental_clinic_tests.core.validators_tests.patient;

import dental_clinic.core.requests.patient.ChangePersonalDataRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.patient.ChangePersonalDataValidator;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.List;

public class ChangePersonalDataValidatorTest extends TestCase {

    ChangePersonalDataValidator validator = new ChangePersonalDataValidator();
    ChangePersonalDataRequest request;
    List<CoreError> returnedErrorsList;

    @Test
    public void testValidSurnameAndPhone() {    //valid surname; valid phone
        String validSurname = "Surname";
        String validPhone = "12345678";
        request = new ChangePersonalDataRequest(1L, validSurname, validPhone);

        returnedErrorsList = validator.validate(request);
        assertEquals(0, returnedErrorsList.size());
    }

    @Test
    public void testValidSurnameAndInvalidPhones() {   //valid surname; INVALID phone
        validator = new ChangePersonalDataValidator();
        String validSurname = "Surname";

        String invalidPhone = "123456789";  //too long
        request = new ChangePersonalDataRequest(1L, validSurname, invalidPhone);
        returnedErrorsList = validator.validate(request);
        assertEquals(1, returnedErrorsList.size());

        invalidPhone = "1234567";   //too short
        request = new ChangePersonalDataRequest(1L, validSurname, invalidPhone);
        returnedErrorsList = validator.validate(request);
        assertEquals(1, returnedErrorsList.size());
    }

    @Test
    public void testInvalidNameAndValidPhone() {    //INVALID surname; valid phone
        String invalidSurname = "Surname99";  //has numbers
        String validPhone = "12345678"; // has 8 digits
        request = new ChangePersonalDataRequest(1L, invalidSurname, validPhone);

        returnedErrorsList = validator.validate(request);
        assertEquals(1, returnedErrorsList.size());

        validPhone = "37112345678"; // has 11 digits
        request = new ChangePersonalDataRequest(1L, invalidSurname, validPhone);

        returnedErrorsList = validator.validate(request);
        assertEquals(1, returnedErrorsList.size());

        validPhone = "791137402888";// has 12 digits
        request = new ChangePersonalDataRequest(1L, invalidSurname, validPhone);

        returnedErrorsList = validator.validate(request);
        assertEquals(1, returnedErrorsList.size());
    }

    @Test
    public void testInvalidNameAndInvalidPhone() {
        String invalidSurname = "Surname222";   //has numbers
        String invalidPhone = "X12345678";    //has a letter
        request = new ChangePersonalDataRequest(1L, invalidSurname, invalidPhone);

        returnedErrorsList = validator.validate(request);
        assertEquals(2, returnedErrorsList.size());
    }

}