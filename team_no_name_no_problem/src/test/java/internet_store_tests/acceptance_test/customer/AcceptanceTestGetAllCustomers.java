package internet_store_tests.acceptance_test.customer;

import internet_store.core.domain.Customer;
import internet_store.core.requests.customer.GetAllCustomersRequest;
import internet_store.core.response.customer.GetAllCustomersResponse;
import internet_store.core.services.customer.GetAllCustomersService;
import internet_store.dependency_injection.ApplicationContext;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class AcceptanceTestGetAllCustomers {

    ApplicationContext applicationContext = new ApplicationContext();

    @Test
    public void test(){
        GetAllCustomersRequest getAllCustomersRequest = new GetAllCustomersRequest();
        GetAllCustomersResponse getAllCustomersResponse = getAllCustomersService().execute(getAllCustomersRequest);


    }

    private GetAllCustomersService getAllCustomersService(){
        return applicationContext.getBean(GetAllCustomersService.class);
    }
}
