package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.TargetListApplication;
import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.target.DeleteTargetRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.services.target.DeleteTargetService;
import java2.application_target_list.core.responses.target.DeleteTargetResponse;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TargetListApplication.class})
public class DeleteTargetAcceptanceTests {

    @Autowired
    private GetAllTargetsService getAllTargetsService;
    @Autowired
    private DeleteTargetService deleteTargetService;
    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private AddTargetService addTargetService;

    private Long targetId;

    @Before
    public void setup() {
        databaseCleaner.clean();
        addTargetsToDB();
    }

    @Test
    public void shouldDeleteTargetFromList_v1() {
        targetId = getAllTargetsService.execute(new GetAllTargetsRequest()).getTargetList().get(0).getId();

        DeleteTargetRequest deleteTargetRequest = new DeleteTargetRequest(targetId);
        DeleteTargetResponse deleteTargetResponse = deleteTargetService.execute(deleteTargetRequest);
        GetAllTargetsResponse response = getAllTargetsService.execute(new GetAllTargetsRequest());

        assertFalse(deleteTargetResponse.hasErrors());
        assertEquals(response.getTargetList().size(), 1);
        assertEquals(response.getTargetList().get(0).getName(), "name2");
        assertEquals(response.getTargetList().get(0).getDescription(), "description2");
    }

    @Test
    public void shouldDeleteTargetFromList_v2() {
        targetId = getAllTargetsService.execute(new GetAllTargetsRequest()).getTargetList().get(1).getId();

        DeleteTargetRequest deleteTargetRequest = new DeleteTargetRequest(targetId);
        DeleteTargetResponse deleteTargetResponse = deleteTargetService.execute(deleteTargetRequest);
        GetAllTargetsResponse response = getAllTargetsService.execute(new GetAllTargetsRequest());

        assertFalse(deleteTargetResponse.hasErrors());
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
        assertEquals(deleteTargetResponse.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(deleteTargetResponse.getErrorList().get(0).getMessage(), "no target with that ID");
    }

    private void addTargetsToDB() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name", "description", 1L);
        AddTargetRequest addTargetRequest2 = new AddTargetRequest("name2", "description2", 4L);
        addTargetService.execute(addTargetRequest1);
        addTargetService.execute(addTargetRequest2);
    }
}
