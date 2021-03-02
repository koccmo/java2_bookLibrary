package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.TargetListApplication;
import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.target.ChangeTargetDeadlineRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.services.target.ChangeTargetDeadlineService;
import java2.application_target_list.core.responses.target.ChangeTargetDeadlineResponse;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TargetListApplication.class})
public class ChangeTargetDeadlineAcceptanceTests {

    @Autowired
    private GetAllTargetsService getAllTargetsService;
    @Autowired
    private ChangeTargetDeadlineService changeTargetDeadlineService;
    @Autowired
    private AddTargetService addTargetService;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    private Long targetId;

    @Before
    public void setup() {
        databaseCleaner.clean();
        addTargetToDB();
    }

    @Test
    public void shouldChangeTargetDeadline() {
        targetId = getAllTargetsService.execute(new GetAllTargetsRequest()).getTargetList().get(0).getId();

        ChangeTargetDeadlineRequest changeTargetDeadlineRequest = new ChangeTargetDeadlineRequest(targetId, 5L);
        ChangeTargetDeadlineResponse changeTargetDeadlineResponse = changeTargetDeadlineService.execute(changeTargetDeadlineRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());

        assertNull(changeTargetDeadlineResponse.getErrorList());
        assertEquals(getAllTargetsResponse.getTargetList().size(), 1);
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "name");
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "description");
        assertEquals(java.util.Optional.ofNullable(getAllTargetsResponse.getTargetList().get(0).getDeadline()), Optional.of(5L));
    }

    @Test
    public void shouldReturnErrorList() {
        targetId = getAllTargetsService.execute(new GetAllTargetsRequest()).getTargetList().get(0).getId();

        ChangeTargetDeadlineRequest changeTargetDeadlineRequest = new ChangeTargetDeadlineRequest(targetId, -5L);
        ChangeTargetDeadlineResponse changeTargetDeadlineResponse = changeTargetDeadlineService.execute(changeTargetDeadlineRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());

        assertTrue(changeTargetDeadlineResponse.hasErrors());
        assertEquals(changeTargetDeadlineResponse.getErrorList().size(), 1);
        assertEquals(changeTargetDeadlineResponse.getErrorList().get(0).getField(), "Target new deadline");
        assertEquals(changeTargetDeadlineResponse.getErrorList().get(0).getMessage(), "must not be negative!");
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "name");
        assertEquals(Optional.ofNullable(getAllTargetsResponse.getTargetList().get(0).getDeadline()), Optional.of(1L));
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "description");
    }

    private void addTargetToDB() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name", "description", 1L);
        addTargetService.execute(addTargetRequest1);
    }
}
