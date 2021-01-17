package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.responses.target.AddTargetResponse;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class AddTargetAcceptanceTests {

    private ApplicationContext applicationContext;
    private AddTargetService addTargetService;
    private GetAllTargetsService getAllTargetsService;

    @Before
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
        addTargetService = applicationContext.getBean(AddTargetService.class);
        getAllTargetsService = applicationContext.getBean(GetAllTargetsService.class);
    }

    @Test
    public void shouldAddTargetsToList() {
        AddTargetRequest request1 = new AddTargetRequest("name", "description", 1);
        AddTargetRequest request2 = new AddTargetRequest("name2", "description2", 4);
        addTargetService.execute(request1);
        addTargetService.execute(request2);
        GetAllTargetsResponse response = getAllTargetsService.execute(new GetAllTargetsRequest());
        assertEquals(response.getTargetList().size(), 2);
        assertNull(response.getErrorList());
        assertEquals(response.getTargetList().get(0).getName(), "name");
        assertEquals(response.getTargetList().get(0).getDescription(), "description");
        assertEquals(response.getTargetList().get(1).getName(), "name2");
        assertEquals(response.getTargetList().get(1).getDescription(), "description2");
    }

    @Test
    public void shouldReturnErrorList() {
        AddTargetRequest request1 = new AddTargetRequest(null, "description", 1);
        AddTargetResponse addTargetResponse = addTargetService.execute(request1);
        assertTrue(addTargetResponse.hasErrors());
        assertEquals(addTargetResponse.getErrorList().size(), 1);
        assertEquals(addTargetResponse.getErrorList().get(0).getField(), "Target name");
        assertEquals(addTargetResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

}
