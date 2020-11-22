package internet_store.core.services.customer;

import internet_store.core.requests.customer.SearchCustomerRequest;
import internet_store.core.response.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchCustomerRequestValidatorTest {

    SearchCustomerRequestValidator searchCustomerRequestValidator = new SearchCustomerRequestValidator();

    @Test
    public void testEmptyFieldSearch(){
        CoreError expectedError = new CoreError("name and surname", "Not valid input for search");

        SearchCustomerRequest searchCustomerRequest = new SearchCustomerRequest(null, "");
        List<CoreError> errors = searchCustomerRequestValidator.validate(searchCustomerRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidFieldForName(){
        SearchCustomerRequest searchCustomerRequest = new SearchCustomerRequest("Jaroslav", "");
        List<CoreError> errors = searchCustomerRequestValidator.validate(searchCustomerRequest);

        assertTrue(errors.size() == 0);

    }

    @Test
    public void testValidFieldForSurname(){
        SearchCustomerRequest searchCustomerRequest = new SearchCustomerRequest(null, "Antonov");
        List<CoreError> errors = searchCustomerRequestValidator.validate(searchCustomerRequest);

        assertTrue(errors.size() == 0);
    }

    @Test
    public void testValidNameAndSurnameFields(){
        SearchCustomerRequest searchCustomerRequest = new SearchCustomerRequest("Jaroslav", "Antonov");
        List<CoreError> errors = searchCustomerRequestValidator.validate(searchCustomerRequest);

        assertTrue(errors.size() == 0);
    }

}