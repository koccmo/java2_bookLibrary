package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.target.ChangeTargetDescriptionRequest;
import java2.application_target_list.core.requests.target.GetAllTargetsRequest;
import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.responses.target.ChangeTargetDescriptionResponse;
import java2.application_target_list.core.responses.target.GetAllTargetsResponse;
import java2.application_target_list.core.services.target.AddTargetService;
import java2.application_target_list.core.services.target.ChangeTargetDescriptionService;
import java2.application_target_list.core.services.target.GetAllTargetsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

import static org.junit.Assert.*;

public class ChangeTargetDescriptionAcceptanceTests {

    private ChangeTargetDescriptionService changeTargetDescriptionService;
    private GetAllTargetsService getAllTargetsService;
    private ApplicationContext applicationContext;
    private DatabaseCleaner databaseCleaner;
    private AddTargetService addTargetService;
    private Long targetId;

    @Before
    public void setup() {
        createServices();
        databaseCleaner.clean();
        addTargetToDB();
    }

    @Test
    public void shouldChangeTargetDescription() {
        targetId = getAllTargetsService.execute(new GetAllTargetsRequest()).getTargetList().get(0).getId();

        ChangeTargetDescriptionRequest changeTargetNameRequest = new ChangeTargetDescriptionRequest(targetId, "New Description");
        ChangeTargetDescriptionResponse changeTargetDescriptionResponse = changeTargetDescriptionService.execute(changeTargetNameRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());

        assertNull(changeTargetDescriptionResponse.getErrorList());
        assertEquals(getAllTargetsResponse.getTargetList().size(), 1);
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "name");
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "New Description");
    }

    @Test
    public void shouldReturnErrorList() {
        targetId = getAllTargetsService.execute(new GetAllTargetsRequest()).getTargetList().get(0).getId();

        ChangeTargetDescriptionRequest changeTargetDescriptionRequest = new ChangeTargetDescriptionRequest(targetId, null);
        ChangeTargetDescriptionResponse changeTargetDescriptionResponse = changeTargetDescriptionService.execute(changeTargetDescriptionRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());

        assertTrue(changeTargetDescriptionResponse.hasErrors());
        assertEquals(changeTargetDescriptionResponse.getErrorList().size(), 1);
        assertEquals(changeTargetDescriptionResponse.getErrorList().get(0).getField(), "Target new description");
        assertEquals(changeTargetDescriptionResponse.getErrorList().get(0).getMessage(), "must not be empty!");
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "name");
        assertEquals(Optional.ofNullable(getAllTargetsResponse.getTargetList().get(0).getDeadline()), Optional.of(1L));
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "description");
    }

    private void createServices() {
        applicationContext = createApplicationContext();
        addTargetService = createAddTargetService();
        getAllTargetsService = createGetAllTargetService();
        databaseCleaner = createDatabaseCleaner();
        changeTargetDescriptionService = createChangeTargetDescriptionService();
    }

    private void addTargetToDB() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name", "description", 1L);
        addTargetService.execute(addTargetRequest1);
    }

    private ChangeTargetDescriptionService createChangeTargetDescriptionService() {
        return applicationContext.getBean(ChangeTargetDescriptionService.class);
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
        return new AnnotationConfigApplicationContext(TargetListConfiguration.class);
    }
}
