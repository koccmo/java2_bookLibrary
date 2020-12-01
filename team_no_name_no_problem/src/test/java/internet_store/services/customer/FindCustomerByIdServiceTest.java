package internet_store.services.customer;

import internet_store.core.domain.Customer;
import internet_store.core.requests.customer.FindCustomerByIdRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.FindCustomerByIdResponse;
import internet_store.core.services.customer.FindCustomerByIdRequestValidator;
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

import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class FindCustomerByIdServiceTest {

    @Mock private CustomerDatabase database;
    @Mock private FindCustomerByIdRequestValidator validator;
    @InjectMocks private FindCustomerByIdService service;

    @Test
    public void testNotValidCustomerIdInput(){
        FindCustomerByIdRequest request = new FindCustomerByIdRequest(-2);
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
    public void testSuccessfullyFindCustomerById(){
        Customer customer = new Customer("name", "surname", "phone", "address",
                "email");
        customer.setId(2L);
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        FindCustomerByIdRequest request = new FindCustomerByIdRequest(2L);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        FindCustomerByIdResponse response = service.execute(request);
        assertTrue(response.hasErrors());
    }

}