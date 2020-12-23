package java2.application_target_list.core.acceptancetests;

import java2.application_target_list.core.requests.AddTargetRequest;
import java2.application_target_list.core.requests.ChangeTargetDeadlineRequest;
import java2.application_target_list.core.requests.GetAllTargetsRequest;
import java2.application_target_list.core.services.ChangeTargetDeadlineService;
import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.responses.ChangeTargetDeadlineResponse;
import java2.application_target_list.core.responses.GetAllTargetsResponse;
import java2.application_target_list.core.services.AddTargetService;
import java2.application_target_list.core.services.GetAllTargetsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ChangeTargetDeadlineAcceptanceTests {

    private ApplicationContext applicationContext;

    @Before
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
    }

    @Test
    public void shouldChangeTargetDeadline() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name", "description", 1);
        AddTargetService addTargetService = applicationContext.getBean(AddTargetService.class);
        addTargetService.execute(addTargetRequest1);

        ChangeTargetDeadlineService changeTargetDeadlineService = applicationContext.getBean(ChangeTargetDeadlineService.class);
        ChangeTargetDeadlineRequest changeTargetDeadlineRequest = new ChangeTargetDeadlineRequest(1L, 5);
        ChangeTargetDeadlineResponse changeTargetDeadlineResponse = changeTargetDeadlineService.execute(changeTargetDeadlineRequest);

        GetAllTargetsService getAllTargetsService = applicationContext.getBean(GetAllTargetsService.class);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());


        assertNull(changeTargetDeadlineResponse.getErrorList());
        assertEquals(getAllTargetsResponse.getTargetList().size(), 1);
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "name");
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "description");
        assertEquals(java.util.Optional.ofNullable(getAllTargetsResponse.getTargetList().get(0).getDeadline()), Optional.of(5));
    }
}
