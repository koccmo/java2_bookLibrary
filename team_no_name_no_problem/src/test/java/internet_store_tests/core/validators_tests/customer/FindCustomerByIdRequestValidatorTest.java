package internet_store_tests.core.validators_tests.customer;

import internet_store.core.requests.customer.FindCustomerByIdRequest;
import internet_store.core.response.CoreError;
import internet_store.core.services.customer.validators.FindCustomerByIdRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FindCustomerByIdRequestValidatorTest {

    FindCustomerByIdRequestValidator findCustomerByIdRequestValidator = new FindCustomerByIdRequestValidator();

    @Test
    public void testNotValidId(){

        CoreError expectedError = new CoreError("id", "Not valid input for id");

        FindCustomerByIdRequest findCustomerByIdRequest = new FindCustomerByIdRequest(-1L);
        List<CoreError> errors = findCustomerByIdRequestValidator.validate(findCustomerByIdRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidId(){

        FindCustomerByIdRequest findCustomerByIdRequest = new FindCustomerByIdRequest(2L);
        List<CoreError> errors = findCustomerByIdRequestValidator.validate(findCustomerByIdRequest);

        assertTrue(errors.size() == 0);
    }

}