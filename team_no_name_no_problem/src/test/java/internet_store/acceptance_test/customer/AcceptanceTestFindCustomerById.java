package internet_store.acceptance_test.customer;

import internet_store.core.domain.Customer;
import internet_store.core.requests.customer.AddCustomerRequest;
import internet_store.core.requests.customer.FindCustomerByIdRequest;
import internet_store.core.requests.customer.GetAllCustomersRequest;
import internet_store.core.response.customer.FindCustomerByIdResponse;
import internet_store.core.response.customer.GetAllCustomersResponse;
import internet_store.core.services.customer.AddCustomerService;
import internet_store.core.services.customer.FindCustomerByIdService;
import internet_store.core.services.customer.GetAllCustomersService;
import internet_store.dependency_injection.ApplicationContext;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AcceptanceTestFindCustomerById {

    ApplicationContext applicationContext = new ApplicationContext();

    @Test
    public void test(){
        Customer customer = new Customer("name", "surname", "number", "address",
                "email");
        Customer customer1 = new Customer("name1", "surname20","phone", "Matisa",
                "tr3vis@Inbox.lv");
        Customer customer2 = new Customer("Valerija", "Lobanova","2781263",
                "Ukraina", "privetpoka@tikto.lv");
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest(customer);
        AddCustomerRequest addCustomerRequest1 = new AddCustomerRequest(customer1);
        AddCustomerRequest addCustomerRequest2 = new AddCustomerRequest(customer2);
        addCustomerService().execute(addCustomerRequest);
        addCustomerService().execute(addCustomerRequest1);
        addCustomerService().execute(addCustomerRequest2);

        FindCustomerByIdRequest findCustomerByIdRequest = new FindCustomerByIdRequest(1L);
        FindCustomerByIdRequest findCustomerByIdRequest1 = new FindCustomerByIdRequest(2L);
        FindCustomerByIdResponse findCustomerByIdResponse =findCustomerByIdService().execute(findCustomerByIdRequest);
        FindCustomerByIdResponse findCustomerByIdResponse1 = findCustomerByIdService().execute(findCustomerByIdRequest1);

        GetAllCustomersRequest getAllCustomersRequest = new GetAllCustomersRequest();
        GetAllCustomersResponse getAllCustomersResponse = getAllCustomersService().execute(getAllCustomersRequest);

        assertTrue(getAllCustomersResponse.getCustomers().size() == 3);
        assertTrue(findCustomerByIdResponse.getCustomer().get().equals(customer));
        assertTrue(findCustomerByIdResponse1.getCustomer().get().equals(customer1));
    }

    private AddCustomerService addCustomerService(){
        return applicationContext.getBean(AddCustomerService.class);
    }

    private FindCustomerByIdService findCustomerByIdService(){
        return applicationContext.getBean(FindCustomerByIdService.class);
    }

    private GetAllCustomersService getAllCustomersService(){
        return applicationContext.getBean(GetAllCustomersService.class);
    }
}
