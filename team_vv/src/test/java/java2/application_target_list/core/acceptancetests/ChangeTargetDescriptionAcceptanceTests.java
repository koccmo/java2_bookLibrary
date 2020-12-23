package java2.application_target_list.core.acceptancetests;

import java2.application_target_list.core.requests.AddTargetRequest;
import java2.application_target_list.core.requests.ChangeTargetDescriptionRequest;
import java2.application_target_list.core.requests.GetAllTargetsRequest;
import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.responses.ChangeTargetDescriptionResponse;
import java2.application_target_list.core.responses.GetAllTargetsResponse;
import java2.application_target_list.core.services.AddTargetService;
import java2.application_target_list.core.services.ChangeTargetDescriptionService;
import java2.application_target_list.core.services.GetAllTargetsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ChangeTargetDescriptionAcceptanceTests {

    private ApplicationContext applicationContext;

    @Before
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
    }
    @Test
    public void shouldChangeTargetDescription() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name", "description", 1);
        AddTargetService addTargetService = applicationContext.getBean(AddTargetService.class);
        addTargetService.execute(addTargetRequest1);

        ChangeTargetDescriptionService changeTargetDescriptionService = applicationContext.getBean(ChangeTargetDescriptionService.class);
        ChangeTargetDescriptionRequest changeTargetNameRequest = new ChangeTargetDescriptionRequest(1L, "New Description");
        ChangeTargetDescriptionResponse changeTargetDescriptionResponse = changeTargetDescriptionService.execute(changeTargetNameRequest);

        GetAllTargetsService getAllTargetsService = applicationContext.getBean(GetAllTargetsService.class);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());


        assertNull(changeTargetDescriptionResponse.getErrorList());
        assertEquals(getAllTargetsResponse.getTargetList().size(), 1);
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "name");
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "New Description");
    }
}
