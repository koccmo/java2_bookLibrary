package internet_store_tests.acceptance_test.customer;

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
import internet_store.dependency_injection.ApplicationContext;
import internet_store.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AcceptanceTestSearchCustomerByName {

    private static  ApplicationContext applicationContext =
            new DIApplicationContextBuilder().build("internet_store");

    @Test
    public void test(){
        Customer customer = new Customer("name", "surname", "82938172", "address",
                "email");
        Customer customer1 = new Customer("name", "surname","29876472", "Matisa",
                "tr3vis@Inbox.lv");
        Customer customer2 = new Customer("Valerija", "Lobanova","2781263",
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
        return applicationContext.getBean(AddCustomerService.class);
    }

    private GetAllCustomersService getAllCustomersService(){
        return applicationContext.getBean(GetAllCustomersService.class);
    }

    private SearchCustomerService searchCustomerService(){
        return applicationContext.getBean(SearchCustomerService.class);
    }
}
