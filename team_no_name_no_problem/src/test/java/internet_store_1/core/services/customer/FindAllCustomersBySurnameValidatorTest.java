package internet_store_1.core.services.customer;

import internet_store_1.core.requests.customer.FindAllCustomersBySurnameRequest;
import internet_store_1.core.response.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FindAllCustomersBySurnameValidatorTest {

    FindAllCustomersBySurnameValidator findAllCustomersBySurnameValidator = new FindAllCustomersBySurnameValidator();

    @Test
    public void testInvalidSurname(){
        CoreError expectedError = new CoreError("surname", "Not valid input for surname");

        FindAllCustomersBySurnameRequest findAllCustomersBySurnameRequest = new FindAllCustomersBySurnameRequest(null);
        List<CoreError> errors = findAllCustomersBySurnameValidator.validate(findAllCustomersBySurnameRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidSurname(){

        FindAllCustomersBySurnameRequest findAllCustomersBySurnameRequest = new FindAllCustomersBySurnameRequest("Rubchinksy");
        List<CoreError> errors = findAllCustomersBySurnameValidator.validate(findAllCustomersBySurnameRequest);

        assertTrue(errors.size() == 0);
    }

}