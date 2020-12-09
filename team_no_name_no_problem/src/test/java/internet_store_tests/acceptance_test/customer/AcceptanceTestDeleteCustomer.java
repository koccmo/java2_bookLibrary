package internet_store_tests.acceptance_test.customer;

import internet_store.core.domain.Customer;
import internet_store.core.requests.customer.AddCustomerRequest;
import internet_store.core.requests.customer.DeleteCustomerRequest;
import internet_store.core.requests.customer.GetAllCustomersRequest;
import internet_store.core.response.customer.GetAllCustomersResponse;
import internet_store.core.services.customer.AddCustomerService;
import internet_store.core.services.customer.DeleteCustomerService;
import internet_store.core.services.customer.GetAllCustomersService;
import internet_store.dependency_injection.ApplicationContext;
import internet_store.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AcceptanceTestDeleteCustomer {

    private static  ApplicationContext applicationContext =
            new DIApplicationContextBuilder().build("internet_store");

    @Test
    public void test(){
        Customer customer = new Customer("Anvar", "Papawa", "1188","Egypt street",
                "vozmimenyazaruku@gmail.com");
        Customer customer1 = new Customer("Jarka", "Zazigalo4ka", "102",
                "AppalonSaturn", "Jazhematj@inbox.lv");
        AddCustomerRequest addCustomerRequest = new AddCustomerRequest(customer);
        AddCustomerRequest addCustomerRequest1 = new AddCustomerRequest(customer1);
        addCustomerService().execute(addCustomerRequest);
        addCustomerService().execute(addCustomerRequest1);

        DeleteCustomerRequest deleteCustomerRequest = new DeleteCustomerRequest(2L);
        deleteCustomerService().execute(deleteCustomerRequest);

        GetAllCustomersRequest getAllCustomersRequest = new GetAllCustomersRequest();
        GetAllCustomersResponse getAllCustomersResponse = getAllCustomersService().execute(getAllCustomersRequest);

        assertTrue(getAllCustomersResponse.getCustomers().size() == 1);
    }

    private DeleteCustomerService deleteCustomerService(){
        return applicationContext.getBean(DeleteCustomerService.class);
    }

    private AddCustomerService addCustomerService(){
        return applicationContext.getBean(AddCustomerService.class);
    }

    private GetAllCustomersService getAllCustomersService(){
        return applicationContext.getBean(GetAllCustomersService.class);
    }
}
