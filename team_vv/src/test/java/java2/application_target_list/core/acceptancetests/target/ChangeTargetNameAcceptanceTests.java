package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.target.ChangeTargetNameRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.services.target.ChangeTargetNameService;
import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.responses.target.ChangeTargetNameResponse;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class ChangeTargetNameAcceptanceTests {

    private ApplicationContext applicationContext;
    private AddTargetService addTargetService;
    private ChangeTargetNameService changeTargetNameService;
    private GetAllTargetsService getAllTargetsService;

    @Before
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
        addTargetService = applicationContext.getBean(AddTargetService.class);
        changeTargetNameService = applicationContext.getBean(ChangeTargetNameService.class);
        getAllTargetsService = applicationContext.getBean(GetAllTargetsService.class);
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name", "description", 1);
        addTargetService.execute(addTargetRequest1);
    }
    @Test
    public void shouldChangeTargetName() {
        ChangeTargetNameRequest changeTargetNameRequest = new ChangeTargetNameRequest(1L, "New Name");
        ChangeTargetNameResponse changeTargetNameResponse = changeTargetNameService.execute(changeTargetNameRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());
        assertNull(changeTargetNameResponse.getErrorList());
        assertEquals(getAllTargetsResponse.getTargetList().size(), 1);
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "New Name");
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "description");
    }

    @Test
    public void shouldReturnErrorList() {
        ChangeTargetNameRequest changeTargetNameRequest = new ChangeTargetNameRequest(21L, "New Name");
        ChangeTargetNameResponse changeTargetNameResponse = changeTargetNameService.execute(changeTargetNameRequest);
        assertTrue(changeTargetNameResponse.hasErrors());
        assertEquals(changeTargetNameResponse.getErrorList().size(), 1);
        assertEquals(changeTargetNameResponse.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(changeTargetNameResponse.getErrorList().get(0).getMessage(), "no target with that ID");
    }
}
