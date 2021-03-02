package java2.application_target_list.core.acceptancetests.target;


import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.target.ChangeTargetNameRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.services.target.ChangeTargetNameService;
import java2.application_target_list.core.responses.target.ChangeTargetNameResponse;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class ChangeTargetNameAcceptanceTests {

    @Autowired
    private AddTargetService addTargetService;
    @Autowired
    private ChangeTargetNameService changeTargetNameService;
    @Autowired
    private GetAllTargetsService getAllTargetsService;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    private Long targetId;

    @BeforeEach
    public void setup() {
        databaseCleaner.clean();
        addTargetToDB();
    }
    @Test
    public void shouldChangeTargetName() {
        targetId = getAllTargetsService.execute(new GetAllTargetsRequest()).getTargetList().get(0).getId();

        ChangeTargetNameRequest changeTargetNameRequest = new ChangeTargetNameRequest(targetId, "New Name");
        ChangeTargetNameResponse changeTargetNameResponse = changeTargetNameService.execute(changeTargetNameRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());

        Assertions.assertNull(changeTargetNameResponse.getErrorList());
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().size(), 1);
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "New Name");
        Assertions.assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "description");
    }

    @Test
    public void shouldReturnErrorList() {
        ChangeTargetNameRequest changeTargetNameRequest = new ChangeTargetNameRequest(21L, "New Name");
        ChangeTargetNameResponse changeTargetNameResponse = changeTargetNameService.execute(changeTargetNameRequest);

        Assertions.assertTrue(changeTargetNameResponse.hasErrors());
        Assertions.assertEquals(changeTargetNameResponse.getErrorList().size(), 1);
        Assertions.assertEquals(changeTargetNameResponse.getErrorList().get(0).getField(), "Target ID;");
        Assertions.assertEquals(changeTargetNameResponse.getErrorList().get(0).getMessage(), "no target with that ID");
    }

    private void addTargetToDB() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name", "description", 1L);
        addTargetService.execute(addTargetRequest1);
    }

}
