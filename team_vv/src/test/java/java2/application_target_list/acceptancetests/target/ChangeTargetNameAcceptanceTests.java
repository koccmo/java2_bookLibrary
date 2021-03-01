package java2.application_target_list.acceptancetests.target;

import java2.application_target_list.TargetListApplication;
import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.target.ChangeTargetNameRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.core.services.target.ChangeTargetNameService;
import java2.application_target_list.config.SpringCoreConfiguration;
import java2.application_target_list.core.responses.target.ChangeTargetNameResponse;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TargetListApplication.class})
public class ChangeTargetNameAcceptanceTests {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private AddTargetService addTargetService;
    @Autowired
    private ChangeTargetNameService changeTargetNameService;
    @Autowired
    private GetAllTargetsService getAllTargetsService;
    @Autowired
    private DatabaseCleaner databaseCleaner;

    private Long targetId;

    @Before
    public void setup() {
        createServices();
        databaseCleaner.clean();
        addTargetToDB();
    }
    @Test
    public void shouldChangeTargetName() {
        targetId = getAllTargetsService.execute(new GetAllTargetsRequest()).getTargetList().get(0).getId();

        ChangeTargetNameRequest changeTargetNameRequest = new ChangeTargetNameRequest(targetId, "New Name");
        ChangeTargetNameResponse changeTargetNameResponse = changeTargetNameService.execute(changeTargetNameRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());

        assertNull(changeTargetNameResponse.getErrorList());
        assertEquals(getAllTargetsResponse.getTargetList().size(), 1);
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "New Name");
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "description");
    }

    @Test
    public void shouldReturnErrorList() {
        ChangeTargetNameRequest changeTargetNameRequest = new ChangeTargetNameRequest(21L, "New Name");
        ChangeTargetNameResponse changeTargetNameResponse = changeTargetNameService.execute(changeTargetNameRequest);

        assertTrue(changeTargetNameResponse.hasErrors());
        assertEquals(changeTargetNameResponse.getErrorList().size(), 1);
        assertEquals(changeTargetNameResponse.getErrorList().get(0).getField(), "Target ID;");
        assertEquals(changeTargetNameResponse.getErrorList().get(0).getMessage(), "no target with that ID");
    }

    private void createServices() {
        applicationContext = createApplicationContext();
        addTargetService = createAddTargetService();
        getAllTargetsService = createGetAllTargetService();
        databaseCleaner = createDatabaseCleaner();
        changeTargetNameService = createChangeTargetNameService();
    }

    private ChangeTargetNameService createChangeTargetNameService() {
        return applicationContext.getBean(ChangeTargetNameService.class);
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

    private void addTargetToDB() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name", "description", 1L);
        addTargetService.execute(addTargetRequest1);
    }

}
