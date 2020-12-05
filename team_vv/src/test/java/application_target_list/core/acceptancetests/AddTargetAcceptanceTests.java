package application_target_list.core.acceptancetests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import application_target_list.dependency_injection.ApplicationContext;
import application_target_list.core.requests.AddTargetRequest;
import application_target_list.core.requests.GetAllTargetsRequest;
import application_target_list.core.responses.GetAllTargetsResponse;
import application_target_list.core.services.AddTargetService;
import application_target_list.core.services.GetAllTargetsService;
import application_target_list.dependency_injection.DIApplicationContextBuilder;
import org.junit.Test;

public class AddTargetAcceptanceTests {

    private static final ApplicationContext applicationContext =
            new DIApplicationContextBuilder().build("application_target_list");

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
