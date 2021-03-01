package java2.application_target_list.acceptancetests.target;

import java2.application_target_list.TargetListApplication;
import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.responses.target.AddTargetResponse;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TargetListApplication.class})
public class AddTargetAcceptanceTests {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private AddTargetService addTargetService;
    @Autowired
    private GetAllTargetsService getAllTargetsService;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Before
    public void setup() {
        databaseCleaner.clean();
    }

    @Test
    public void shouldAddTargetsToList() {
        AddTargetRequest request1 = new AddTargetRequest("name", "description", 1L);
        AddTargetRequest request2 = new AddTargetRequest("name2", "description2", 4L);

        AddTargetResponse addTargetResponse1 = addTargetService.execute(request1);
        AddTargetResponse addTargetResponse2 = addTargetService.execute(request2);
        GetAllTargetsResponse response = getAllTargetsService.execute(new GetAllTargetsRequest());

        assertFalse(addTargetResponse1.hasErrors());
        assertFalse(addTargetResponse2.hasErrors());
        assertEquals(response.getTargetList().size(), 2);
        assertNull(response.getErrorList());
        assertEquals(response.getTargetList().get(0).getName(), "name");
        assertEquals(response.getTargetList().get(0).getDescription(), "description");
        assertEquals(response.getTargetList().get(1).getName(), "name2");
        assertEquals(response.getTargetList().get(1).getDescription(), "description2");
    }

    @Test
    public void shouldReturnErrorList() {
        AddTargetRequest request1 = new AddTargetRequest(null, "description", 1L);
        AddTargetResponse addTargetResponse = addTargetService.execute(request1);
        assertTrue(addTargetResponse.hasErrors());
        assertEquals(addTargetResponse.getErrorList().size(), 1);
        assertEquals(addTargetResponse.getErrorList().get(0).getField(), "Target name");
        assertEquals(addTargetResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }
}
