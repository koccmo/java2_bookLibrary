package application_target_list.core.acceptancetests;

import application_target_list.ApplicationContext;
import application_target_list.core.database.Target;
import application_target_list.core.requests.AddTargetRequest;
import application_target_list.core.requests.DeleteTargetRequest;
import application_target_list.core.requests.GetAllTargetsRequest;
import application_target_list.core.responses.DeleteTargetResponse;
import application_target_list.core.responses.GetAllTargetsResponse;
import application_target_list.core.services.AddTargetService;
import application_target_list.core.services.DeleteTargetService;
import application_target_list.core.services.GetAllTargetsService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DeleteTargetAcceptanceTests {

    private ApplicationContext applicationContext = new ApplicationContext();

    @Test
    public void shouldDeleteTargetFromList_v1() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name", "description", 1);
        AddTargetRequest addTargetRequest2 = new AddTargetRequest("name2", "description2", 4);
        AddTargetService addTargetService = applicationContext.getBean(AddTargetService.class);
        addTargetService.execute(addTargetRequest1);
        addTargetService.execute(addTargetRequest2);
        DeleteTargetService deleteTargetService = applicationContext.getBean(DeleteTargetService.class);
        DeleteTargetRequest deleteTargetRequest = new DeleteTargetRequest(1L);
        deleteTargetService.execute(deleteTargetRequest);
        GetAllTargetsService getAllTargetsService = applicationContext.getBean(GetAllTargetsService.class);
        GetAllTargetsResponse response = getAllTargetsService.execute(new GetAllTargetsRequest());
        assertEquals(response.getTargetList().size(), 1);
        assertNull(response.getErrorList());
        assertEquals(response.getTargetList().get(0).getName(), "name2");
        assertEquals(response.getTargetList().get(0).getDescription(), "description2");
    }

    @Test
    public void shouldDeleteTargetFromList_v2() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name", "description", 1);
        AddTargetRequest addTargetRequest2 = new AddTargetRequest("name2", "description2", 4);
        AddTargetService addTargetService = applicationContext.getBean(AddTargetService.class);
        addTargetService.execute(addTargetRequest1);
        addTargetService.execute(addTargetRequest2);
        DeleteTargetService deleteTargetService = applicationContext.getBean(DeleteTargetService.class);
        DeleteTargetRequest deleteTargetRequest = new DeleteTargetRequest(2L);
        deleteTargetService.execute(deleteTargetRequest);
        GetAllTargetsService getAllTargetsService = applicationContext.getBean(GetAllTargetsService.class);
        GetAllTargetsResponse response = getAllTargetsService.execute(new GetAllTargetsRequest());
        assertEquals(response.getTargetList().size(), 1);
        assertNull(response.getErrorList());
        assertEquals(response.getTargetList().get(0).getName(), "name");
        assertEquals(response.getTargetList().get(0).getDescription(), "description");
    }

    @Test
    public void shouldReturnErrorsList() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name", "description", 1);
        AddTargetRequest addTargetRequest2 = new AddTargetRequest("name2", "description2", 4);
        AddTargetService addTargetService = applicationContext.getBean(AddTargetService.class);
        addTargetService.execute(addTargetRequest1);
        addTargetService.execute(addTargetRequest2);
        DeleteTargetService deleteTargetService = applicationContext.getBean(DeleteTargetService.class);
        DeleteTargetRequest deleteTargetRequest = new DeleteTargetRequest(3L);
        DeleteTargetResponse deleteTargetResponse = deleteTargetService.execute(deleteTargetRequest);
        assertEquals(deleteTargetResponse.getErrorList().size(), 1);
    }

}
