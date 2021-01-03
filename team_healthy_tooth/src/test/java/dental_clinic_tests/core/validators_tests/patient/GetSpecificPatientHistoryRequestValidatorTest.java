package dental_clinic_tests.core.validators_tests.patient;

import dental_clinic.core.requests.patient.GetSpecificPatientHistoryRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.patient.GetSpecificPatientHistoryRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GetSpecificPatientHistoryRequestValidatorTest {

    GetSpecificPatientHistoryRequestValidator getSpecificPatientHistoryRequestValidator = new GetSpecificPatientHistoryRequestValidator();

    @Test
    public void testNotValidId(){
        CoreError expectedError = new CoreError("id", "Not valid input for id");

        GetSpecificPatientHistoryRequest getSpecificPatientHistoryRequest = new GetSpecificPatientHistoryRequest(-8L);
        List<CoreError> errors = getSpecificPatientHistoryRequestValidator.validate(getSpecificPatientHistoryRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidId(){

        GetSpecificPatientHistoryRequest getSpecificPatientHistoryRequest = new GetSpecificPatientHistoryRequest(2L);
        List<CoreError> errors = getSpecificPatientHistoryRequestValidator.validate(getSpecificPatientHistoryRequest);

        assertTrue(errors.isEmpty());
    }

}
