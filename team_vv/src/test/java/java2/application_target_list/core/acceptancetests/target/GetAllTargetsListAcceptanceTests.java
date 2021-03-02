package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class GetAllTargetsListAcceptanceTests {

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
    public void shouldReturnTargetsList() {
        AddTargetRequest request1 = new AddTargetRequest("name", "description", 1L);
        addTargetService.execute(request1);
        GetAllTargetsResponse response = getAllTargetsService.execute(new GetAllTargetsRequest());

        Assertions.assertFalse(response.hasErrors());
        Assertions.assertEquals(response.getTargetList().size(), 1);
        Assertions.assertNull(response.getErrorList());
        Assertions.assertEquals(response.getTargetList().get(0).getName(), "name");
        Assertions.assertEquals(response.getTargetList().get(0).getDescription(), "description");
    }
}
