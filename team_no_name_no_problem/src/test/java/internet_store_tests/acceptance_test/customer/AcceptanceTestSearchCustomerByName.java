package internet_store_tests.acceptance_test.customer;

import internet_store.config.MainMenuConfiguration;
import internet_store.core.domain.Customer;
import internet_store.core.requests.Ordering;
import internet_store.core.requests.Paging;
import internet_store.core.requests.customer.AddCustomerRequest;
import internet_store.core.requests.customer.GetAllCustomersRequest;
import internet_store.core.requests.customer.SearchCustomerRequest;
import internet_store.core.response.customer.GetAllCustomersResponse;
import internet_store.core.response.customer.SearchCustomerResponse;
import internet_store.core.services.customer.AddCustomerService;
import internet_store.core.services.customer.GetAllCustomersService;
import internet_store.core.services.customer.SearchCustomerService;
import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertTrue;

public class AcceptanceTestSearchCustomerByName {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(MainMenuConfiguration.class);
    }
    @Test
    public void test(){
        Customer customer = new Customer("name", "surname", "82938172", "address",
                "email@email.lv");
        Customer customer1 = new Customer("name", "surname","29876472", "Matisa",
                "tr3vis@Inbox.lv");
        Customer customer2 = new Customer("Valerija", "Lobanova","27815263",
                "Ukraina", "privetpoka@tikto.lv");
        Ordering ordering = new Ordering("name", "ASC");
        Paging paging = new Paging(1,3);

        AddCustomerRequest addCustomerRequest = new AddCustomerRequest(customer);
        AddCustomerRequest addCustomerRequest1 = new AddCustomerRequest(customer1);
        AddCustomerRequest addCustomerRequest2 = new AddCustomerRequest(customer2);
        addCustomerService().execute(addCustomerRequest);
        addCustomerService().execute(addCustomerRequest1);
        addCustomerService().execute(addCustomerRequest2);

        SearchCustomerRequest searchCustomerRequest = new SearchCustomerRequest("name", "surname",
                ordering, paging);
        SearchCustomerRequest searchCustomerRequest1 = new SearchCustomerRequest("Valerija", "Lobanova",
                ordering, paging);
        SearchCustomerResponse searchCustomerResponse = searchCustomerService().execute(searchCustomerRequest);
        SearchCustomerResponse searchCustomerResponse1 = searchCustomerService().execute(searchCustomerRequest1);

        GetAllCustomersRequest getAllCustomersRequest = new GetAllCustomersRequest();
        GetAllCustomersResponse getAllCustomersResponse = getAllCustomersService().execute(getAllCustomersRequest);

        assertTrue(getAllCustomersResponse.getCustomers().size() == 3);
        assertTrue(searchCustomerResponse.getCustomers().get(0).equals(customer));
        assertTrue(searchCustomerResponse1.getCustomers().get(0).equals(customer2));
    }

    private AddCustomerService addCustomerService(){
        return appContext.getBean(AddCustomerService.class);
    }

    private GetAllCustomersService getAllCustomersService(){
        return appContext.getBean(GetAllCustomersService.class);
    }

    private SearchCustomerService searchCustomerService(){
        return appContext.getBean(SearchCustomerService.class);
    }
}
