package internet_store_tests.core.services_tests.customer;

import internet_store.core.domain.Customer;
import internet_store.core.requests.customer.AddCustomerRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.AddCustomerResponse;
import internet_store.core.services.customer.validators.AddCustomerRequestValidator;
import internet_store.core.services.customer.AddCustomerService;
import internet_store.database.customer.CustomerDatabase;
import internet_store_tests.core.services_tests.matchers.CustomerMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class AddCustomerServiceTest {

    @Mock private CustomerDatabase customerDatabase;
    @Mock private AddCustomerRequestValidator addCustomerRequestValidator;
    @InjectMocks private AddCustomerService addCustomerService;

    @Test
    public void testNotValidSurnameFieldRequest(){
        Customer customer = new Customer("Jaroslav", null, "28736181", "Matisa 31-44",
                "tr3vis@inbox.lv" );
        AddCustomerRequest request = new AddCustomerRequest(customer);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError = new CoreError("surname", "Not valid input for surname");
        errors.add(expectedError);
        Mockito.when(addCustomerRequestValidator.validate(request)).thenReturn(errors);

        AddCustomerResponse response = addCustomerService.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().contains(expectedError), true);

        Mockito.verifyNoInteractions(customerDatabase);
    }

    @Test
    public void testNotValidAllFieldsEmptyRequest(){
        Customer customer = new Customer("", null, null, "", "");
        AddCustomerRequest request = new AddCustomerRequest(customer);
        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError1 = new CoreError("name", "Not valid input for name");
        CoreError expectedError2 = new CoreError("surname", "Not valid input for surname");
        CoreError expectedError3 = new CoreError("phone number", "Not valid input for phone number");
        CoreError expectedError4 = new CoreError("address", "Not valid input for address");
        CoreError expectedError5 = new CoreError("email", "Not valid input for email");
        errors.add(expectedError1);
        errors.add(expectedError2);
        errors.add(expectedError3);
        errors.add(expectedError4);
        errors.add(expectedError5);
        Mockito.when(addCustomerRequestValidator.validate(request)).thenReturn(errors);

        AddCustomerResponse response = addCustomerService.execute(request);
        assertTrue(response.hasErrors());
        assertTrue(response.getErrors().size() == 5);
        assertTrue(response.getErrors().contains(expectedError1));
        assertTrue(response.getErrors().contains(expectedError2));
        assertTrue(response.getErrors().contains(expectedError3));
        assertTrue(response.getErrors().contains(expectedError4));
        assertTrue(response.getErrors().contains(expectedError5));
        Mockito.verifyNoInteractions(customerDatabase);

    }

    @Test
    public void testDatabaseContainsSameCustomer(){
        Customer customer = new Customer("Jaroslav", "Brutan", "28736181","Matisa 31-44",
                "tr3vis@inbox.lv");
        AddCustomerRequest request = new AddCustomerRequest(customer);

        List<CoreError> errors = new ArrayList<>();
        CoreError expectedError = new CoreError("database", "Database contains the same customer");
        errors.add(expectedError);
        Mockito.when(addCustomerRequestValidator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(customerDatabase.containsCustomer(request.getCustomer())).thenReturn(true);

        AddCustomerResponse response = addCustomerService.execute(request);
        assertEquals(response.hasErrors(), true);
        assertTrue(response.getErrors().contains(expectedError));
        assertEquals(response.getErrors().size(), 1);
        assertTrue(response.getErrors().contains(expectedError));
    }


    @Test
    public void testCustomerAddedSuccessfully(){
        Customer customer = new Customer("name", "surname", "1287432", "Brivibas 120",
                "privet@gmail.com");
        AddCustomerRequest request = new AddCustomerRequest(customer);
        Mockito.when(addCustomerRequestValidator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(customerDatabase.containsCustomer(request.getCustomer())).thenReturn(false);

        AddCustomerResponse addCustomerResponse = addCustomerService.execute(request);
        assertTrue(!addCustomerResponse.hasErrors());
        assertTrue(addCustomerResponse.getCustomer().equals(customer));
        Mockito.verify(customerDatabase).addCustomer(argThat(new CustomerMatcher("name", "surname", "1287432")));
    }


}