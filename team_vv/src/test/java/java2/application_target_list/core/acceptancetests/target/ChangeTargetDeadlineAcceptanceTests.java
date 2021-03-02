package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.target.ChangeTargetDeadlineRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.services.target.ChangeTargetDeadlineService;
import java2.application_target_list.core.responses.target.ChangeTargetDeadlineResponse;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@SpringBootTest
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

    @BeforeEach
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

        Assertions.assertNull(changeTargetDeadlineResponse.getErrorList());
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().size(), 1);
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "name");
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "description");
        Assertions.assertEquals(java.util.Optional.ofNullable(getAllTargetsResponse.getTargetList().get(0).getDeadline()), Optional.of(5L));
    }

    @Test
    public void shouldReturnErrorList() {
        targetId = getAllTargetsService.execute(new GetAllTargetsRequest()).getTargetList().get(0).getId();

        ChangeTargetDeadlineRequest changeTargetDeadlineRequest = new ChangeTargetDeadlineRequest(targetId, -5L);
        ChangeTargetDeadlineResponse changeTargetDeadlineResponse = changeTargetDeadlineService.execute(changeTargetDeadlineRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());

        Assertions.assertTrue(changeTargetDeadlineResponse.hasErrors());
        Assertions.assertEquals(changeTargetDeadlineResponse.getErrorList().size(), 1);
        Assertions.assertEquals(changeTargetDeadlineResponse.getErrorList().get(0).getField(), "Target new deadline");
        Assertions.assertEquals(changeTargetDeadlineResponse.getErrorList().get(0).getMessage(), "must not be negative!");
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "name");
        Assertions.assertEquals(Optional.ofNullable(getAllTargetsResponse.getTargetList().get(0).getDeadline()), Optional.of(1L));
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "description");
    }

    private void addTargetToDB() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name", "description", 1L);
        addTargetService.execute(addTargetRequest1);
    }
}
