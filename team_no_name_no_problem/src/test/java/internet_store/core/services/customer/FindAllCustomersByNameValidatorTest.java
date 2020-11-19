package internet_store.core.services.customer;

import internet_store.core.requests.customer.FindAllCustomersByNameRequest;
import internet_store.core.response.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FindAllCustomersByNameValidatorTest {

    FindAllCustomersByNameValidator findAllCustomersByNameValidator = new FindAllCustomersByNameValidator();

    @Test
    public void testInvalidName(){
        CoreError expectedError = new CoreError("name", "Not valid input for name");

        FindAllCustomersByNameRequest findAllCustomersByNameRequest = new FindAllCustomersByNameRequest(null);
        List<CoreError> errors = findAllCustomersByNameValidator.validate(findAllCustomersByNameRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidName(){

        FindAllCustomersByNameRequest findAllCustomersByNameRequest = new FindAllCustomersByNameRequest("Gowa");
        List<CoreError> errors = findAllCustomersByNameValidator.validate(findAllCustomersByNameRequest);

        assertTrue(errors.size() == 0);
    }

}