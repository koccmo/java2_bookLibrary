package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.responses.target.AddTargetResponse;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class AddTargetAcceptanceTests {

    @Autowired
    private AddTargetService addTargetService;
    @Autowired
    private GetAllTargetsService getAllTargetsService;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    @BeforeEach
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

        Assertions.assertFalse(addTargetResponse1.hasErrors());
        Assertions.assertFalse(addTargetResponse2.hasErrors());
        Assertions.assertEquals(response.getTargetList().size(), 2);
        Assertions.assertNull(response.getErrorList());
        Assertions.assertEquals(response.getTargetList().get(0).getName(), "name");
        Assertions.assertEquals(response.getTargetList().get(0).getDescription(), "description");
        Assertions.assertEquals(response.getTargetList().get(1).getName(), "name2");
        Assertions.assertEquals(response.getTargetList().get(1).getDescription(), "description2");
    }

    @Test
    public void shouldReturnErrorList() {
        AddTargetRequest request1 = new AddTargetRequest(null, "description", 1L);
        AddTargetResponse addTargetResponse = addTargetService.execute(request1);
        Assertions.assertTrue(addTargetResponse.hasErrors());
        Assertions.assertEquals(addTargetResponse.getErrorList().size(), 1);
        Assertions.assertEquals(addTargetResponse.getErrorList().get(0).getField(), "Target name");
        Assertions.assertEquals(addTargetResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }
}
