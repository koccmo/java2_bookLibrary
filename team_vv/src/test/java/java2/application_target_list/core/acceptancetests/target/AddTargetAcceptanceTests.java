package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.responses.target.AddTargetResponse;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import java2.application_target_list.config.SpringCoreConfiguration;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.*;

public class AddTargetAcceptanceTests {

    private ApplicationContext applicationContext;
    private AddTargetService addTargetService;
    private GetAllTargetsService getAllTargetsService;
    private DatabaseCleaner databaseCleaner;

    @Before
    public void setup() {
        createServices();
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

    private void createServices() {
        applicationContext = createApplicationContext();
        addTargetService = createAddTargetService();
        getAllTargetsService = createGetAllTargetService();
        databaseCleaner = createDatabaseCleaner();
    }

    private DatabaseCleaner createDatabaseCleaner() {
        return applicationContext.getBean(DatabaseCleaner.class);
    }

    private GetAllTargetsService createGetAllTargetService() {
        return applicationContext.getBean(GetAllTargetsService.class);
    }

    private AddTargetService createAddTargetService() {
        return applicationContext.getBean(AddTargetService.class);
    }

    private ApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
    }

}
