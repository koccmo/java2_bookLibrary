package internet_store.acceptance_test.customer;

import internet_store.ApplicationContext;
import internet_store.core.domain.Customer;
import internet_store.core.requests.customer.AddCustomerRequest;
import internet_store.core.requests.customer.GetAllCustomersRequest;
import internet_store.core.response.customer.GetAllCustomersResponse;
import internet_store.core.services.customer.AddCustomerService;
import internet_store.core.services.customer.GetAllCustomersService;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AcceptanceTestAddCustomer {

    ApplicationContext applicationContext = new ApplicationContext();

    @Test
    public void test(){
        Customer customer = new Customer("Jarik","Brutaxa","28450116", "Matisa 31",
                "tr3vis@inbox.lv");
        AddCustomerRequest request = new AddCustomerRequest(customer);
        addCustomerService().execute(request);

        GetAllCustomersRequest getAllCustomersRequest = new GetAllCustomersRequest();
        GetAllCustomersResponse getAllCustomersResponse = getAllCustomersService().execute(getAllCustomersRequest);

        assertTrue(getAllCustomersResponse.getCustomers().size() == 1);
    }

    private AddCustomerService addCustomerService(){
        return applicationContext.getBean(AddCustomerService.class);
    }

    private GetAllCustomersService getAllCustomersService(){
        return applicationContext.getBean(GetAllCustomersService.class);
    }
}
