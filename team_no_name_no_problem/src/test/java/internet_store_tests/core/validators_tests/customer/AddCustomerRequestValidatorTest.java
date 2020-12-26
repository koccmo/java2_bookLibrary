package internet_store_tests.core.validators_tests.customer;

import internet_store.core.domain.Customer;
import internet_store.core.requests.customer.AddCustomerRequest;
import internet_store.core.response.CoreError;
import internet_store.core.services.customer.validators.AddCustomerRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AddCustomerRequestValidatorTest {

    AddCustomerRequestValidator addCustomerRequestValidator = new AddCustomerRequestValidator();

    @Test
    public void testInvalidInputName(){
        CoreError expectedError = new CoreError("name", "Name can't be empty");
        CoreError expectedError2 = new CoreError("name", "Not valid input for name, should contain only letters");

        Customer customer = new Customer("","Jablokov","28765291","Matisa 33-42",
                "otvali@gmail.com");
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest(customer);
        List<CoreError> errors = addCustomerRequestValidator.validate(addCustomerRequest);

        assertTrue(errors.size() == 2);
        assertTrue(errors.contains(expectedError));
        assertTrue(errors.contains(expectedError2));
    }

    @Test
    public void testInvalidInputSurname(){
        CoreError expectedError = new CoreError("surname", "Surname can't be empty");
        CoreError expectedError2 = new CoreError("surname", "Not valid input for surname, should contain only letters");

        Customer customer = new Customer("Jarik","","28765291","Matisa 33-42",
                "otvali@gmail.com");
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest(customer);
        List<CoreError> errors = addCustomerRequestValidator.validate(addCustomerRequest);

        assertTrue(errors.size() == 2);
        assertTrue(errors.contains(expectedError));
        assertTrue(errors.contains(expectedError2));
    }

    @Test
    public void testInvalidInputPhoneNumber(){
        CoreError expectedError = new CoreError("phone number", "Phone can't be empty");
        CoreError expectedError2 = new CoreError("phone number", "Not valid input for phone number, should contain only 8 or 11 digits");

        Customer customer = new Customer("Jarik","Jablokov",null,"Matisa 33-42",
                "otvali@gmail.com");
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest(customer);
        List<CoreError> errors = addCustomerRequestValidator.validate(addCustomerRequest);

        assertTrue(errors.size() == 2);
        assertTrue(errors.contains(expectedError));
        assertTrue(errors.contains(expectedError2));
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
        CoreError expectedError = new CoreError("e-mail", "E-mail can't be empty");

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