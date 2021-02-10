package internet_store_tests.acceptance_test.customer;

import internet_store.config.SpringCoreConfiguration;
import internet_store.core.domain.Customer;
import internet_store.core.requests.customer.AddCustomerRequest;
import internet_store.core.requests.customer.GetAllCustomersRequest;
import internet_store.core.response.customer.GetAllCustomersResponse;
import internet_store.core.services.customer.AddCustomerService;
import internet_store.core.services.customer.GetAllCustomersService;
import internet_store.core.DatabaseCleaner;
import org.springframework.context.ApplicationContext;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import static org.junit.Assert.assertTrue;

public class AcceptanceTest {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
        getDatabaseCleaner().clean();

    }
    @Test
    public void test() {
        Customer customer = new Customer("Johny", "Bravo", "28736810", "Queens",
                "bravo@gmail.com");
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest(customer);
        addCustomerService().execute(addCustomerRequest);

        Customer customer2 = new Customer("Danik", "Ivanov", "82771827", "Riga",
                "ivanov@gmail.com");
        AddCustomerRequest addCustomerRequest2 = new AddCustomerRequest(customer2);
        addCustomerService().execute(addCustomerRequest2);

        GetAllCustomersRequest getAllCustomersRequest = new GetAllCustomersRequest();
        GetAllCustomersResponse getAllCustomersResponse = getAllCustomersService().execute(getAllCustomersRequest);

        assertTrue(getAllCustomersResponse.getCustomers().size() == 2);
    }

    private AddCustomerService addCustomerService() {
        return appContext.getBean(AddCustomerService.class);
    }

    private GetAllCustomersService getAllCustomersService() {
        return appContext.getBean(GetAllCustomersService.class);
    }

    private DatabaseCleaner getDatabaseCleaner(){ return appContext.getBean(DatabaseCleaner.class);}
}
