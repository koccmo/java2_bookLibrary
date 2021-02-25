package internet_store_tests.core.services_tests.customer;

import internet_store.core.domain.Customer;
import internet_store.core.requests.customer.FindCustomerByIdRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.FindCustomerByIdResponse;
import internet_store.core.services.customer.validators.FindCustomerByIdRequestValidator;
import internet_store.core.services.customer.FindCustomerByIdService;
import internet_store.database.customer.CustomerDatabase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class FindCustomerByIdServiceTest {

    @Mock private CustomerDatabase database;
    @Mock private FindCustomerByIdRequestValidator validator;
    @InjectMocks private FindCustomerByIdService service;

    @Test
    public void testNotValidCustomerIdInput(){
        FindCustomerByIdRequest request = new FindCustomerByIdRequest(null);
        CoreError expectedError = new CoreError("id", "Not valid input for id");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);
        Mockito.when(validator.validate(request)).thenReturn(errors);

        FindCustomerByIdResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertTrue(response.getErrors().contains(expectedError));
        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void testNoIdInDatabase(){
        FindCustomerByIdRequest request = new FindCustomerByIdRequest(2L);
        CoreError expectedError = new CoreError("database", "Database doesn't contain customer with id 2");

        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.findById(2L)).thenReturn(Optional.empty());

        FindCustomerByIdResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertTrue(response.getErrors().contains(expectedError));
    }

    @Test
    public void testSuccessfullyFindCustomerById(){
        Customer customer = new Customer("name", "surname", "phone", "address",
                "email");
        customer.setId(2L);
        FindCustomerByIdRequest request = new FindCustomerByIdRequest(2L);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.findById(2L)).thenReturn(Optional.of(customer));

        FindCustomerByIdResponse response = service.execute(request);
        assertFalse(response.hasErrors());
       // assertTrue(response.getCustomers().get().equals(customer));
    }

}