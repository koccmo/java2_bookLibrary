package java2.application_target_list.core.acceptancetests;

import java2.application_target_list.core.requests.AddTargetRequest;
import java2.application_target_list.core.requests.GetAllTargetsRequest;
import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.responses.GetAllTargetsResponse;
import java2.application_target_list.core.services.AddTargetService;
import java2.application_target_list.core.services.GetAllTargetsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GetAllTargetsListAcceptanceTests {

    private ApplicationContext applicationContext;

    @Before
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
    }

    @Test
    public void shouldReturnTargetsList() {
        AddTargetRequest request1 = new AddTargetRequest("name", "description", 1);
        AddTargetService addTargetService = applicationContext.getBean(AddTargetService.class);
        addTargetService.execute(request1);
        GetAllTargetsService getAllTargetsService = applicationContext.getBean(GetAllTargetsService.class);
        GetAllTargetsResponse response = getAllTargetsService.execute(new GetAllTargetsRequest());
        assertEquals(response.getTargetList().size(), 1);
        assertNull(response.getErrorList());
        assertEquals(response.getTargetList().get(0).getName(), "name");
        assertEquals(response.getTargetList().get(0).getDescription(), "description");
    }
}
