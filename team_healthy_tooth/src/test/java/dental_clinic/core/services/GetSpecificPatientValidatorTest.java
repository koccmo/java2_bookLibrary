package dental_clinic.core.services;

import dental_clinic.core.requests.GetSpecificPatientHistoryRequest;
import dental_clinic.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GetSpecificPatientValidatorTest {

    GetSpecificPatientValidator getSpecificPatientValidator = new GetSpecificPatientValidator();

    @Test
    public void testNotValidId(){
        CoreError expectedError = new CoreError("id", "Not valid input for id");

        GetSpecificPatientHistoryRequest getSpecificPatientHistoryRequest = new GetSpecificPatientHistoryRequest(-8);
        List<CoreError> errors = getSpecificPatientValidator.validate(getSpecificPatientHistoryRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidId(){

        GetSpecificPatientHistoryRequest getSpecificPatientHistoryRequest = new GetSpecificPatientHistoryRequest(2);
        List<CoreError> errors = getSpecificPatientValidator.validate(getSpecificPatientHistoryRequest);

        assertTrue(errors.isEmpty());
    }

}
