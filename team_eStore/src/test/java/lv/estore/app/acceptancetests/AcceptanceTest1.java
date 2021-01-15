package lv.estore.app.acceptancetests;

import lv.estore.app.config.EStoreConfiguration;
import lv.estore.app.core.request.AddRequest;
import lv.estore.app.core.request.GetAllRequest;
import lv.estore.app.core.responses.GetAllResponse;
import lv.estore.app.core.services.AddService;
import lv.estore.app.core.services.GetAllService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest1 {

    private ApplicationContext appContext;

    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(EStoreConfiguration.class);
    }

    @Test
    public void testAddProduct_ReturnCorrectProductList() {
        AddRequest addRequest1 = new AddRequest("name", "description", "1.0");
        getAddService().execute(addRequest1);

        AddRequest addRequest2 = new AddRequest("name", "description2", "2.0");
        getAddService().execute(addRequest2);

        GetAllResponse response = getAllService().execute(new GetAllRequest());
        assertEquals(2, response.getProducts().size());
    }

    private AddService getAddService() {
        return appContext.getBean(AddService.class);
    }

    private GetAllService getAllService() {
        return appContext.getBean(GetAllService.class);
    }
}
