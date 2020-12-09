package internet_store_tests.core.services_tests.customer;

import internet_store.core.domain.Customer;
import internet_store.core.requests.customer.GetAllCustomersRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.GetAllCustomersResponse;
import internet_store.core.services.customer.GetAllCustomersService;
import internet_store.core.services.customer.validators.GetAllCustomersValidator;
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
public class GetAllCustomersServiceTest {

    @Mock private CustomerDatabase database;
    @Mock private GetAllCustomersValidator validator;
    @InjectMocks private GetAllCustomersService service;

    @Test
    public void testNoCustomersInDatabase(){
        CoreError expectedError = new CoreError("database", "Database is empty");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);

        GetAllCustomersRequest request = new GetAllCustomersRequest();
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.getCustomers()).thenReturn(new ArrayList<>());

        GetAllCustomersResponse response = service.execute(request);

        assertTrue(response.hasErrors());
        assertFalse(response.getErrors().contains(expectedError));
        assertTrue(response.getErrors().size() == 1);
    }

    @Test
    public void testReturnListOfCustomers(){
        Customer customer = new Customer("name", "surname", "phone", "address",
                "email");
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        GetAllCustomersRequest request = new GetAllCustomersRequest();
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.getCustomers()).thenReturn(customers);

        GetAllCustomersResponse response = service.execute(request);

        assertFalse(response.hasErrors());
        assertTrue(response.getCustomers().equals(customers));
    }

}