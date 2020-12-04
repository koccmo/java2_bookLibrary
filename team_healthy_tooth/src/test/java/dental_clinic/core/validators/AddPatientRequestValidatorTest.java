package dental_clinic.core.validators;

import dental_clinic.core.domain.PersonalData;
import dental_clinic.core.requests.AddPatientRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.services.validators.AddPatientRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AddPatientRequestValidatorTest {

    AddPatientRequestValidator addPatientRequestValidator = new AddPatientRequestValidator();

    @Test
    public void testNotValidName(){
        CoreError coreError = new CoreError("Personal data : name", "Not valid input for name");

        PersonalData personalData = new PersonalData(null, "Surname", "12345678", "12345678900");
        AddPatientRequest addPatientRequest = new AddPatientRequest(personalData);

        List<CoreError> errors = addPatientRequestValidator.validate(addPatientRequest);
        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(coreError));
    }

    @Test
    public void testNotValidSurname(){
        CoreError coreError = new CoreError("Personal data : surname", "Not valid input for surname");

        PersonalData personalData = new PersonalData("name", "", "12345678", "12345678900");
        AddPatientRequest addPatientRequest = new AddPatientRequest(personalData);

        List<CoreError> errors = addPatientRequestValidator.validate(addPatientRequest);
        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(coreError));
    }

    @Test
    public void testNotValidPhone(){
        CoreError coreError = new CoreError("Personal data : phone", "Not valid input for phone");

        PersonalData personalData = new PersonalData("name", "Surname", "avbd4", "12345678900");
        AddPatientRequest addPatientRequest = new AddPatientRequest(personalData);

        List<CoreError> errors = addPatientRequestValidator.validate(addPatientRequest);
        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(coreError));
    }

    @Test
    public void testNotValidPhoneWrongLength(){
        CoreError coreError = new CoreError("Personal data : phone", "Not valid input for phone");

        PersonalData personalData = new PersonalData("name", "Surname", "12546", "12345678900");
        AddPatientRequest addPatientRequest = new AddPatientRequest(personalData);

        List<CoreError> errors = addPatientRequestValidator.validate(addPatientRequest);
        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(coreError));
    }

    @Test
    public void testNotValidPersonalCodeLetters(){
        CoreError coreError = new CoreError("Personal data : personal code", "Not valid input for personal code");

        PersonalData personalData = new PersonalData("name", "Surname", "12345678", "adfds");
        AddPatientRequest addPatientRequest = new AddPatientRequest(personalData);

        List<CoreError> errors = addPatientRequestValidator.validate(addPatientRequest);
        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(coreError));
    }

    @Test
    public void testNotValidPersonalCodeWrongLength(){
        CoreError coreError = new CoreError("Personal data : personal code", "Not valid input for personal code");

        PersonalData personalData = new PersonalData("name", "Surname", "12345678", "1235");
        AddPatientRequest addPatientRequest = new AddPatientRequest(personalData);

        List<CoreError> errors = addPatientRequestValidator.validate(addPatientRequest);
        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(coreError));
    }

}