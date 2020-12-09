package internet_store_tests.core.validators_tests.customer;

import internet_store.core.requests.customer.DeleteCustomerRequest;
import internet_store.core.response.CoreError;
import internet_store.core.services.customer.validators.DeleteCustomerRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DeleteCustomerRequestValidatorTest {

    DeleteCustomerRequestValidator deleteCustomerRequestValidator = new DeleteCustomerRequestValidator();

    @Test
    public void testNotValidId(){
        CoreError expectedError = new CoreError("id", "Not valid input for id");

        DeleteCustomerRequest deleteCustomerRequest = new DeleteCustomerRequest(-1L);
        List<CoreError> errors = deleteCustomerRequestValidator.validate(deleteCustomerRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidInput(){
        DeleteCustomerRequest deleteCustomerRequest = new DeleteCustomerRequest(2L);
        List<CoreError> errors = deleteCustomerRequestValidator.validate(deleteCustomerRequest);

        assertTrue(errors.isEmpty());
    }

}