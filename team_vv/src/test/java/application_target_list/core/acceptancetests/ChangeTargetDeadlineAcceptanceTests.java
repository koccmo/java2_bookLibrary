package application_target_list.core.acceptancetests;

import application_target_list.dependency_injection.ApplicationContext;
import application_target_list.core.requests.AddTargetRequest;
import application_target_list.core.requests.ChangeTargetDeadlineRequest;
import application_target_list.core.requests.GetAllTargetsRequest;
import application_target_list.core.responses.ChangeTargetDeadlineResponse;
import application_target_list.core.responses.GetAllTargetsResponse;
import application_target_list.core.services.AddTargetService;
import application_target_list.core.services.ChangeTargetDeadlineService;
import application_target_list.core.services.GetAllTargetsService;
import application_target_list.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ChangeTargetDeadlineAcceptanceTests {

    private static final ApplicationContext applicationContext =
            new DIApplicationContextBuilder().build("application_target_list");

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
