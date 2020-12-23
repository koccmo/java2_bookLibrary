package java2.application_target_list.core.acceptancetests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java2.application_target_list.core.requests.AddTargetRequest;
import java2.application_target_list.core.requests.GetAllTargetsRequest;
import java2.application_target_list.core.services.GetAllTargetsService;
import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.responses.GetAllTargetsResponse;
import java2.application_target_list.core.services.AddTargetService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AddTargetAcceptanceTests {
    private ApplicationContext applicationContext;

    @Before
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
    }

    @Test
    public void shouldAddTargetsToList() {
        AddTargetRequest request1 = new AddTargetRequest("name", "description", 1);
        AddTargetRequest request2 = new AddTargetRequest("name2", "description2", 4);
        AddTargetService addTargetService = applicationContext.getBean(AddTargetService.class);
        addTargetService.execute(request1);
        addTargetService.execute(request2);
        GetAllTargetsService getAllTargetsService = applicationContext.getBean(GetAllTargetsService.class);
        GetAllTargetsResponse response = getAllTargetsService.execute(new GetAllTargetsRequest());
        assertEquals(response.getTargetList().size(), 2);
        assertNull(response.getErrorList());
        assertEquals(response.getTargetList().get(0).getName(), "name");
        assertEquals(response.getTargetList().get(0).getDescription(), "description");
        assertEquals(response.getTargetList().get(1).getName(), "name2");
        assertEquals(response.getTargetList().get(1).getDescription(), "description2");
    }

}
