package lv.estore.app.core.acceptancetests;

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

    private ApplicationContext applicationContext;

    @Before
    public void setup(){
        applicationContext = new AnnotationConfigApplicationContext(EStoreConfiguration.class);
    }

    @Test
    public void shouldReturnCorrectBookList() {
        AddRequest addRequest1 = new AddRequest("product", "description", "1.1");
        getAddService().execute(addRequest1);

        AddRequest addRequest2 = new AddRequest("product", "description","2.2");
        getAddService().execute(addRequest2);

        GetAllResponse response = getAllService().execute(new GetAllRequest());
        assertEquals(response.getProducts().size(), 2);
    }

    private AddService getAddService(){
        return applicationContext.getBean(AddService.class);
    }

    private GetAllService getAllService(){
        return applicationContext.getBean(GetAllService.class);
    }

}
