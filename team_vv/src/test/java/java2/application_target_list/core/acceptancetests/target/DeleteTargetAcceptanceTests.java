package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.target.DeleteTargetRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.services.target.DeleteTargetService;
import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.responses.target.DeleteTargetResponse;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import java2.application_target_list.core.services.target.GetAllTargetsService;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DeleteTargetAcceptanceTests {

    private ApplicationContext applicationContext;
    private AddTargetService addTargetService;
    private GetAllTargetsService getAllTargetsService;
    private DeleteTargetService deleteTargetService;

    @Before
    public void setup() {
        applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
        getAllTargetsService = applicationContext.getBean(GetAllTargetsService.class);
        addTargetService = applicationContext.getBean(AddTargetService.class);
        deleteTargetService = applicationContext.getBean(DeleteTargetService.class);
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name", "description", 1);
        AddTargetRequest addTargetRequest2 = new AddTargetRequest("name2", "description2", 4);
        addTargetService.execute(addTargetRequest1);
        addTargetService.execute(addTargetRequest2);
    }

    @Test
    public void shouldDeleteTargetFromList_v1() {
        DeleteTargetRequest deleteTargetRequest = new DeleteTargetRequest(1L);
        deleteTargetService.execute(deleteTargetRequest);
        GetAllTargetsResponse response = getAllTargetsService.execute(new GetAllTargetsRequest());
        assertEquals(response.getTargetList().size(), 1);
        assertEquals(response.getTargetList().get(0).getName(), "name2");
        assertEquals(response.getTargetList().get(0).getDescription(), "description2");
    }

    @Test
    public void shouldDeleteTargetFromList_v2() {
        DeleteTargetRequest deleteTargetRequest = new DeleteTargetRequest(2L);
        deleteTargetService.execute(deleteTargetRequest);
        GetAllTargetsResponse response = getAllTargetsService.execute(new GetAllTargetsRequest());
        assertEquals(response.getTargetList().size(), 1);
        assertNull(response.getErrorList());
        assertEquals(response.getTargetList().get(0).getName(), "name");
        assertEquals(response.getTargetList().get(0).getDescription(), "description");
    }

    @Test
    public void shouldReturnErrorsList() {
        DeleteTargetRequest deleteTargetRequest = new DeleteTargetRequest(3L);
        DeleteTargetResponse deleteTargetResponse = deleteTargetService.execute(deleteTargetRequest);
        assertEquals(deleteTargetResponse.getErrorList().size(), 1);
    }

}
