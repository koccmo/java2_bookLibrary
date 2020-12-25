package internet_store_tests.acceptance_test.customer;

import internet_store.config.MainMenuConfiguration;
import internet_store.core.requests.customer.GetAllCustomersRequest;
import internet_store.core.response.customer.GetAllCustomersResponse;
import internet_store.core.services.customer.GetAllCustomersService;
import org.springframework.context.ApplicationContext;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertTrue;

public class AcceptanceTestGetAllCustomers {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(MainMenuConfiguration.class);
    }
    @Test
    public void test(){
        GetAllCustomersRequest getAllCustomersRequest = new GetAllCustomersRequest();
        GetAllCustomersResponse getAllCustomersResponse = getAllCustomersService().execute(getAllCustomersRequest);


    }

    private GetAllCustomersService getAllCustomersService(){
        return appContext.getBean(GetAllCustomersService.class);
    }
}
