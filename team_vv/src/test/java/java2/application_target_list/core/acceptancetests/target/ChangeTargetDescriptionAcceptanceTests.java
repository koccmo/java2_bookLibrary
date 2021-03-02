package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.target.ChangeTargetDescriptionRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.responses.target.ChangeTargetDescriptionResponse;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import java2.application_target_list.core.services.target.ChangeTargetDescriptionService;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@SpringBootTest
public class ChangeTargetDescriptionAcceptanceTests {

    @Autowired
    private ChangeTargetDescriptionService changeTargetDescriptionService;
    @Autowired
    private GetAllTargetsService getAllTargetsService;
    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private AddTargetService addTargetService;

    private Long targetId;

    @BeforeEach
    public void setup() {
        databaseCleaner.clean();
        addTargetToDB();
    }

    @Test
    public void shouldChangeTargetDescription() {
        targetId = getAllTargetsService.execute(new GetAllTargetsRequest()).getTargetList().get(0).getId();

        ChangeTargetDescriptionRequest changeTargetNameRequest = new ChangeTargetDescriptionRequest(targetId, "New Description");
        ChangeTargetDescriptionResponse changeTargetDescriptionResponse = changeTargetDescriptionService.execute(changeTargetNameRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());

        Assertions.assertNull(changeTargetDescriptionResponse.getErrorList());
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().size(), 1);
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "name");
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "New Description");
    }

    @Test
    public void shouldReturnErrorList() {
        targetId = getAllTargetsService.execute(new GetAllTargetsRequest()).getTargetList().get(0).getId();

        ChangeTargetDescriptionRequest changeTargetDescriptionRequest = new ChangeTargetDescriptionRequest(targetId, null);
        ChangeTargetDescriptionResponse changeTargetDescriptionResponse = changeTargetDescriptionService.execute(changeTargetDescriptionRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());

        Assertions.assertTrue(changeTargetDescriptionResponse.hasErrors());
        Assertions.assertEquals(changeTargetDescriptionResponse.getErrorList().size(), 1);
        Assertions.assertEquals(changeTargetDescriptionResponse.getErrorList().get(0).getField(), "Target new description");
        Assertions.assertEquals(changeTargetDescriptionResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "name");
        Assertions.assertEquals(Optional.ofNullable(getAllTargetsResponse.getTargetList().get(0).getDeadline()), Optional.of(1L));
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "description");
    }

    private void addTargetToDB() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name", "description", 1L);
        addTargetService.execute(addTargetRequest1);
    }
}
