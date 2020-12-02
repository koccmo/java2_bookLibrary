package internet_store.services.customer;

import internet_store.core.domain.Customer;
import internet_store.core.requests.Ordering;
import internet_store.core.requests.Paging;
import internet_store.core.requests.customer.SearchCustomerRequest;
import internet_store.core.response.CoreError;
import internet_store.core.response.customer.SearchCustomerResponse;
import internet_store.core.services.customer.SearchCustomerRequestValidator;
import internet_store.core.services.customer.SearchCustomerService;
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
public class SearchCustomerServiceTest {

    @Mock
    private CustomerDatabase database;
    @Mock
    private SearchCustomerRequestValidator validator;
    @InjectMocks
    private SearchCustomerService service;

    private Ordering ordering = new Ordering("surname", "ASC");
    private Paging paging = new Paging(1, 2);

    @Test
    public void testEmptySearch() {
        CoreError expectedError = new CoreError("search", "Not valid input for search");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);

        SearchCustomerRequest request = new SearchCustomerRequest("", "", ordering, paging);

        Mockito.when(validator.validate(request)).thenReturn(errors);

        SearchCustomerResponse response = service.execute(request);

        assertTrue(response.hasErrors());
        assertTrue(response.getErrors().contains(expectedError));
        Mockito.verifyNoInteractions(database);
    }

    @Test
    public void testNoCustomerWithSuchNameAndSurname() {
        CoreError expectedError =
                new CoreError("database", "Database doesn't contain customer with name Jarik and surname Bratuha");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);

        SearchCustomerRequest request = new SearchCustomerRequest("Jarik", "Bratuha", ordering, paging);

        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.findCustomersByNameAndSurname(request.getName(),
                request.getSurname())).thenReturn(new ArrayList<>());

        SearchCustomerResponse response = service.execute(request);

        assertTrue(response.hasErrors());
        assertTrue(response.getErrors().contains(expectedError));

        assertTrue(response.getErrors().size() == 1);
    }

    @Test
    public void testNoCustomerWithSuchName() {
        CoreError expectedError =
                new CoreError("database", "Database doesn't contain customer with name: Jarik");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);

        SearchCustomerRequest request = new SearchCustomerRequest("Jarik", null, ordering, paging);

        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.findAllCustomersByName(request.getName())).thenReturn(new ArrayList<>());

        SearchCustomerResponse response = service.execute(request);

        assertTrue(response.hasErrors());
        assertTrue(response.getErrors().contains(expectedError));
        assertTrue(response.getErrors().size() == 1);
    }

    @Test
    public void testNoCustomerWithSuchSurname() {
        CoreError expectedError =
                new CoreError("database", "Database doesn't contain customer with surname: Brutaxa");
        List<CoreError> errors = new ArrayList<>();
        errors.add(expectedError);

        SearchCustomerRequest request = new SearchCustomerRequest("", "Brutaxa",
                ordering, paging);

        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.findAllCustomersBySurname(request.getSurname())).thenReturn(new ArrayList<>());

        SearchCustomerResponse response = service.execute(request);

        assertTrue(response.hasErrors());
        assertTrue(response.getErrors().contains(expectedError));
        assertTrue(response.getErrors().size() == 1);
    }

    @Test
    public void testSearchBySurname() {
        Customer customer = new Customer("Jarik", "Brutaxa", "phone",
                "address", "email");
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);

        SearchCustomerRequest request = new SearchCustomerRequest(null, "Brutaxa", ordering,
                paging);

        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());
        Mockito.when(database.findAllCustomersBySurname(request.getSurname())).thenReturn(customers);

        SearchCustomerResponse response = service.execute(request);

        assertFalse(response.hasErrors());
        assertTrue(response.getCustomers().size() == 1);
        assertTrue(response.getCustomers().contains(customer));
        assertTrue(response.getCustomers().get(0).equals(customer));
    }
}