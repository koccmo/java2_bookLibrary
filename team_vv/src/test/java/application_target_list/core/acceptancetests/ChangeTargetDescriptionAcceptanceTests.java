package application_target_list.core.acceptancetests;

import application_target_list.dependency_injection.ApplicationContext;
import application_target_list.core.requests.AddTargetRequest;
import application_target_list.core.requests.ChangeTargetDescriptionRequest;
import application_target_list.core.requests.GetAllTargetsRequest;
import application_target_list.core.responses.ChangeTargetDescriptionResponse;
import application_target_list.core.responses.GetAllTargetsResponse;
import application_target_list.core.services.AddTargetService;
import application_target_list.core.services.ChangeTargetDescriptionService;
import application_target_list.core.services.GetAllTargetsService;
import application_target_list.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ChangeTargetDescriptionAcceptanceTests {

    private static final ApplicationContext applicationContext =
            new DIApplicationContextBuilder().build("application_target_list");

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
