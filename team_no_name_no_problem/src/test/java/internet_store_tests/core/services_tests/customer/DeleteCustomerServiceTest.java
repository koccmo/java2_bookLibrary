package internet_store_tests.core.services_tests.customer;

import internet_store.core.domain.Customer;
import internet_store.core.requests.customer.DeleteCustomerRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.DeleteCustomerResponse;
import internet_store.core.services.customer.validators.DeleteCustomerRequestValidator;
import internet_store.core.services.customer.DeleteCustomerService;
import internet_store.database.customer.CustomerDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class DeleteCustomerServiceTest {

    @Mock private DeleteCustomerRequestValidator deleteCustomerRequestValidator;
    @Mock private CustomerDatabase customerDatabase;
    @InjectMocks private DeleteCustomerService deleteCustomerService;

    @Test
    public void testNotValidIdInput(){
        DeleteCustomerRequest request = new DeleteCustomerRequest(-1L);
        CoreError expectedError = new CoreError("id", "Not valid input for id");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);
        Mockito.when(deleteCustomerRequestValidator.validate(request)).thenReturn(errors);

        DeleteCustomerResponse response = deleteCustomerService.execute(request);
        assertTrue(response.hasErrors());
        assertTrue(response.getErrors().contains(expectedError));
        Mockito.verifyNoInteractions(customerDatabase);
    }

    @Test
    public void testNoIdInDatabase(){
        DeleteCustomerRequest request = new DeleteCustomerRequest(10L);
        CoreError expectedError = new CoreError("database", "database doesn't contain this customer 10");

        Mockito.when(deleteCustomerRequestValidator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(customerDatabase.containsId(request.getId())).thenReturn(false);

        DeleteCustomerResponse response = deleteCustomerService.execute(request);
        assertTrue(response.hasErrors());
        assertTrue(response.getErrors().contains(expectedError));
        assertTrue(response.getErrors().size() == 1);
    }

    @Test
    public void testDeletedSuccessfully(){
        Customer customer = new Customer("name", "surname", "2987361", "address",
                "brutaxa@gmail.com");
        customer.setId(1L);
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        DeleteCustomerRequest request = new DeleteCustomerRequest(1L);
        Mockito.when(deleteCustomerRequestValidator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(customerDatabase.containsId(request.getId())).thenReturn(true);
        Mockito.when(customerDatabase.getCustomers()).thenReturn(customers);

        DeleteCustomerResponse response = deleteCustomerService.execute(request);

        assertFalse(response.hasErrors());
    }
}