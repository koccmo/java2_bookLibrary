package internet_store_1.core.services.customer;

import internet_store_1.core.domain.Customer;
import internet_store_1.core.requests.customer.AddCustomerRequest;
import internet_store_1.core.response.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AddCustomerRequestValidatorTest {

    AddCustomerRequestValidator addCustomerRequestValidator = new AddCustomerRequestValidator();

    @Test
    public void testInvalidInputName(){
        CoreError expectedError = new CoreError("name", "Not valid input for name");

        Customer customer = new Customer("","Jablokov","28765291","Matisa 33-42",
                "otvali@gmail.com");
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest(customer);
        List<CoreError> errors = addCustomerRequestValidator.validate(addCustomerRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testInvalidInputSurname(){
        CoreError expectedError = new CoreError("surname", "Not valid input for surname");

        Customer customer = new Customer("Jarik","","28765291","Matisa 33-42",
                "otvali@gmail.com");
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest(customer);
        List<CoreError> errors = addCustomerRequestValidator.validate(addCustomerRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testInvalidInputPhoneNumber(){
        CoreError expectedError = new CoreError("phone number", "Not valid input for phone number");

        Customer customer = new Customer("Jarik","Jablokov",null,"Matisa 33-42",
                "otvali@gmail.com");
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest(customer);
        List<CoreError> errors = addCustomerRequestValidator.validate(addCustomerRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testInvalidInputAddress(){
        CoreError expectedError = new CoreError("address", "Not valid input for address");

        Customer customer = new Customer("Jarik","Jablokov","28765291","",
                "otvali@gmail.com");
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest(customer);
        List<CoreError> errors = addCustomerRequestValidator.validate(addCustomerRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testInvalidInputEmail(){
        CoreError expectedError = new CoreError("e-mail", "Not valid input for e-mail");

        Customer customer = new Customer("Jarik","Jablokov","28765291","Matisa 33-42",
                "");
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest(customer);
        List<CoreError> errors = addCustomerRequestValidator.validate(addCustomerRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidInput(){

        Customer customer = new Customer("Jarik","Jablokov","28765291","Matisa 33-42",
                "otvali@gmail.com");
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest(customer);
        List<CoreError> errors = addCustomerRequestValidator.validate(addCustomerRequest);

        assertTrue(errors.isEmpty());
    }
}