package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.target.ChangeTargetDescriptionRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.responses.target.ChangeTargetDescriptionResponse;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import java2.application_target_list.core.services.target.ChangeTargetDescriptionService;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

import static org.junit.Assert.*;

public class ChangeTargetDescriptionAcceptanceTests {

    private ApplicationContext applicationContext;
    private ChangeTargetDescriptionService changeTargetDescriptionService;
    private GetAllTargetsService getAllTargetsService;
    private AddTargetService addTargetService;

    @Before
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
        addTargetService = applicationContext.getBean(AddTargetService.class);
        getAllTargetsService = applicationContext.getBean(GetAllTargetsService.class);
        changeTargetDescriptionService = applicationContext.getBean(ChangeTargetDescriptionService.class);
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name", "description", 1);
        addTargetService.execute(addTargetRequest1);

    }
    @Test
    public void shouldChangeTargetDescription() {
        ChangeTargetDescriptionRequest changeTargetNameRequest = new ChangeTargetDescriptionRequest(1L, "New Description");
        ChangeTargetDescriptionResponse changeTargetDescriptionResponse = changeTargetDescriptionService.execute(changeTargetNameRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());
        assertNull(changeTargetDescriptionResponse.getErrorList());
        assertEquals(getAllTargetsResponse.getTargetList().size(), 1);
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "name");
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "New Description");
    }

    @Test
    public void shouldReturnErrorList() {
        ChangeTargetDescriptionRequest changeTargetDescriptionRequest = new ChangeTargetDescriptionRequest(1L, null);
        ChangeTargetDescriptionResponse changeTargetDescriptionResponse = changeTargetDescriptionService.execute(changeTargetDescriptionRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());
        assertTrue(changeTargetDescriptionResponse.hasErrors());
        assertEquals(changeTargetDescriptionResponse.getErrorList().size(), 1);
        assertEquals(changeTargetDescriptionResponse.getErrorList().get(0).getField(), "Target new description");
        assertEquals(changeTargetDescriptionResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "name");
        assertEquals(Optional.ofNullable(getAllTargetsResponse.getTargetList().get(0).getDeadline()), Optional.of(1));
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "description");
    }
}
