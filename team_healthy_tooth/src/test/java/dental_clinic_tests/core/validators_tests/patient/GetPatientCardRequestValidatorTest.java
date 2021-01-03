package dental_clinic_tests.core.validators_tests.patient;

import dental_clinic.core.requests.patient.GetPatientCardRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.patient.GetPatientCardRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class GetPatientCardRequestValidatorTest {

    GetPatientCardRequestValidator validator = new GetPatientCardRequestValidator();
    GetPatientCardRequest request;
    List<CoreError> returnedErrors;
    CoreError neededError = new CoreError("id", "Not valid input for id");

    @Test
    public void testNullId() {
        request = new GetPatientCardRequest(null);
        returnedErrors = validator.validate(request);

        assertEquals(1, returnedErrors.size());
        assertEquals(neededError, returnedErrors.get(0));
    }

    @Test
    public void testNegativeId() {
        request = new GetPatientCardRequest(-5L);
        returnedErrors = validator.validate(request);

        assertEquals(1, returnedErrors.size());
        assertEquals(neededError, returnedErrors.get(0));
    }

    @Test
    public void testValidId() {
        request = new GetPatientCardRequest(10L);
        returnedErrors = validator.validate(request);

        assertEquals(0, returnedErrors.size());
    }

}