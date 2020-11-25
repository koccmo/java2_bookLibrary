package internet_store_1.core.services.customer;

import internet_store_1.core.requests.customer.FindCustomerByIdRequest;
import internet_store_1.core.response.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FindCustomerByIdRequestValidatorTest {

    FindCustomerByIdRequestValidator findCustomerByIdRequestValidator = new FindCustomerByIdRequestValidator();

    @Test
    public void testNotValidId(){

        CoreError expectedError = new CoreError("id", "Not valid input for id");

        FindCustomerByIdRequest findCustomerByIdRequest = new FindCustomerByIdRequest(-1);
        List<CoreError> errors = findCustomerByIdRequestValidator.validate(findCustomerByIdRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidId(){

        FindCustomerByIdRequest findCustomerByIdRequest = new FindCustomerByIdRequest(2);
        List<CoreError> errors = findCustomerByIdRequestValidator.validate(findCustomerByIdRequest);

        assertTrue(errors.size() == 0);
    }

}