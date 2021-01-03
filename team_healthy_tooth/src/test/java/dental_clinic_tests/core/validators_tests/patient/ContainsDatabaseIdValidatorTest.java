package dental_clinic_tests.core.validators_tests.patient;

import dental_clinic.core.requests.ContainsDatabaseIdRequest;
import dental_clinic.core.responses.CoreError;
import dental_clinic.core.validators.ContainsDatabaseIdValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ContainsDatabaseIdValidatorTest {

    ContainsDatabaseIdValidator validator = new ContainsDatabaseIdValidator();
    ContainsDatabaseIdRequest request;
    List<CoreError> returnedErrors;
    CoreError neededError = new CoreError("id = ", "Invalid input patient ID!");

    @Test
    public void testNullId() {
        request = new ContainsDatabaseIdRequest(null);
        returnedErrors = validator.validate(request);

        assertEquals(1, returnedErrors.size());
        assertEquals(returnedErrors.get(0), neededError);
    }

    @Test
    public void testNegativeId() {
        request = new ContainsDatabaseIdRequest(-5L);
        returnedErrors = validator.validate(request);

        assertEquals(1, returnedErrors.size());
        assertEquals(returnedErrors.get(0), neededError);
    }

    @Test
    public void testValidId() {
        request = new ContainsDatabaseIdRequest(1L);
        returnedErrors = validator.validate(request);

        assertEquals(0, returnedErrors.size());
    }
}