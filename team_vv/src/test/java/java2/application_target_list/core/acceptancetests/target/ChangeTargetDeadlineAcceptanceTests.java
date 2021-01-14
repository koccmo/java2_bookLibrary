package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.target.ChangeTargetDeadlineRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.services.target.ChangeTargetDeadlineService;
import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.responses.target.ChangeTargetDeadlineResponse;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

import static org.junit.Assert.*;

public class ChangeTargetDeadlineAcceptanceTests {

    private ApplicationContext applicationContext;
    private AddTargetService addTargetService;
    private GetAllTargetsService getAllTargetsService;
    private ChangeTargetDeadlineService changeTargetDeadlineService;

    @Before
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
        addTargetService = applicationContext.getBean(AddTargetService.class);
        getAllTargetsService = applicationContext.getBean(GetAllTargetsService.class);
        changeTargetDeadlineService = applicationContext.getBean(ChangeTargetDeadlineService.class);
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name", "description", 1);
        addTargetService.execute(addTargetRequest1);
    }

    @Test
    public void shouldChangeTargetDeadline() {
        ChangeTargetDeadlineRequest changeTargetDeadlineRequest = new ChangeTargetDeadlineRequest(1L, 5);
        ChangeTargetDeadlineResponse changeTargetDeadlineResponse = changeTargetDeadlineService.execute(changeTargetDeadlineRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());
        assertNull(changeTargetDeadlineResponse.getErrorList());
        assertEquals(getAllTargetsResponse.getTargetList().size(), 1);
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "name");
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "description");
        assertEquals(java.util.Optional.ofNullable(getAllTargetsResponse.getTargetList().get(0).getDeadline()), Optional.of(5));
    }

    @Test
    public void shouldReturnErrorList() {
        ChangeTargetDeadlineRequest changeTargetDeadlineRequest = new ChangeTargetDeadlineRequest(1L, -5);
        ChangeTargetDeadlineResponse changeTargetDeadlineResponse = changeTargetDeadlineService.execute(changeTargetDeadlineRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());
        assertTrue(changeTargetDeadlineResponse.hasErrors());
        assertEquals(changeTargetDeadlineResponse.getErrorList().size(), 1);
        assertEquals(changeTargetDeadlineResponse.getErrorList().get(0).getField(), "Target new deadline");
        assertEquals(changeTargetDeadlineResponse.getErrorList().get(0).getMessage(), "must not be negative!");
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "name");
        assertEquals(Optional.ofNullable(getAllTargetsResponse.getTargetList().get(0).getDeadline()), Optional.of(1));
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "description");
    }
}
