package java2.application_target_list.core.acceptancetests.target;

import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.requests.target.*;
import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.responses.target.*;
import java2.application_target_list.core.services.target.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

import static org.junit.Assert.*;

public class AcceptanceTests {


    private ChangeTargetNameService changeTargetNameService;
    private AddTargetService addTargetService;
    private ChangeTargetDescriptionService changeTargetDescriptionService;
    private ChangeTargetDeadlineService changeTargetDeadlineService;
    private SearchTargetByNameService searchTargetByNameService;
    private ApplicationContext applicationContext;
    private DatabaseCleaner databaseCleaner;
    private GetAllTargetsService getAllTargetsService;
    private DeleteTargetService deleteTargetService;
    private SearchTargetByDescriptionService searchTargetByDescriptionService;
    private Long targetId;

    @Before
    public void setup() {
        createServices();
        databaseCleaner.clean();
    }

    @Test
    public void test() {
        addTargetsToDB();
        deleteFirstTargetFromDB();
        changeThirdTargetParameters();
        searchTargetsByName();
        searchTargetsByDescription();
    }

    private void searchTargetsByDescription() {
        SearchTargetByDescriptionRequest searchTargetByDescriptionRequest = new SearchTargetByDescriptionRequest("description");
        SearchTargetByDescriptionResponse searchTargetByDescriptionResponse = searchTargetByDescriptionService.execute(searchTargetByDescriptionRequest);

        assertFalse(searchTargetByDescriptionResponse.hasErrors());
        assertEquals(searchTargetByDescriptionResponse.getTargetList().size(), 3);
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getName(), "name2");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(0).getDescription(), "description2");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(0).getDeadline()), Optional.of(4L));
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getName(), "name3");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(1).getDescription(), "description3");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(1).getDeadline()), Optional.of(6L));
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getName(), "New name");
        assertEquals(searchTargetByDescriptionResponse.getTargetList().get(2).getDescription(), "New description");
        assertEquals(Optional.ofNullable(searchTargetByDescriptionResponse.getTargetList().get(2).getDeadline()), Optional.of(100L));


    }

    private void searchTargetsByName() {
        SearchTargetByNameRequest searchTargetByNameRequest = new SearchTargetByNameRequest("New name");
        SearchTargetByNameResponse searchTargetByNameResponse = searchTargetByNameService.execute(searchTargetByNameRequest);

        assertNull(searchTargetByNameResponse.getErrorList());
        assertEquals(searchTargetByNameResponse.getTargetsList().size(), 1);
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getName(), "New name");
        assertEquals(searchTargetByNameResponse.getTargetsList().get(0).getDescription(), "New description");
        assertEquals(java.util.Optional.ofNullable(searchTargetByNameResponse.getTargetsList().get(0).getDeadline()), Optional.of(100L));
    }


    private void changeThirdTargetParameters() {
        targetId = getAllTargetsService.execute(new GetAllTargetsRequest()).getTargetList().get(2).getId();
        ChangeTargetNameRequest changeTargetNameRequest = new ChangeTargetNameRequest(targetId, "New name");
        ChangeTargetNameResponse changeTargetNameResponse = changeTargetNameService.execute(changeTargetNameRequest);

        ChangeTargetDeadlineRequest changeTargetDeadlineRequest = new ChangeTargetDeadlineRequest(targetId, 100L);
        ChangeTargetDeadlineResponse changeTargetDeadlineResponse = changeTargetDeadlineService.execute(changeTargetDeadlineRequest);

        ChangeTargetDescriptionRequest changeTargetDescriptionRequest = new ChangeTargetDescriptionRequest(targetId, "New description");
        ChangeTargetDescriptionResponse changeTargetDescriptionResponse = changeTargetDescriptionService.execute(changeTargetDescriptionRequest);

        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());

        assertNull(changeTargetNameResponse.getErrorList());
        assertNull(changeTargetDeadlineResponse.getErrorList());
        assertNull(changeTargetDescriptionResponse.getErrorList());
        assertEquals(getAllTargetsResponse.getTargetList().size(), 3);
        assertEquals(getAllTargetsResponse.getTargetList().get(2).getName(), "New name");
        assertEquals(getAllTargetsResponse.getTargetList().get(2).getDescription(), "New description");
        assertEquals(Optional.ofNullable(getAllTargetsResponse.getTargetList().get(2).getDeadline()), Optional.of(100L));
    }

    private void deleteFirstTargetFromDB(){
        targetId = getAllTargetsService.execute(new GetAllTargetsRequest()).getTargetList().get(0).getId();

        DeleteTargetRequest deleteTargetRequest = new DeleteTargetRequest(targetId);
        DeleteTargetResponse deleteTargetResponse = deleteTargetService.execute(deleteTargetRequest);
        GetAllTargetsResponse getAllTargetsResponse = getAllTargetsService.execute(new GetAllTargetsRequest());

        assertFalse(deleteTargetResponse.hasErrors());
        assertEquals(getAllTargetsResponse.getTargetList().size(), 3);
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getName(), "name2");
        assertEquals(getAllTargetsResponse.getTargetList().get(0).getDescription(), "description2");
        assertEquals(Optional.ofNullable(getAllTargetsResponse.getTargetList().get(0).getDeadline()), Optional.of(4L));
        assertEquals(getAllTargetsResponse.getTargetList().get(1).getName(), "name3");
        assertEquals(getAllTargetsResponse.getTargetList().get(1).getDescription(), "description3");
        assertEquals(Optional.ofNullable(getAllTargetsResponse.getTargetList().get(1).getDeadline()), Optional.of(6L));
        assertEquals(getAllTargetsResponse.getTargetList().get(2).getName(), "wdc");
        assertEquals(getAllTargetsResponse.getTargetList().get(2).getDescription(), "sda");
        assertEquals(Optional.ofNullable(getAllTargetsResponse.getTargetList().get(2).getDeadline()), Optional.of(156L));
    }

    private void addTargetsToDB() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name1", "description1", 1L);
        AddTargetRequest addTargetRequest2 = new AddTargetRequest("name2", "description2", 4L);
        AddTargetRequest addTargetRequest3 = new AddTargetRequest("name3", "description3", 6L);
        AddTargetRequest addTargetRequest4 = new AddTargetRequest("wdc", "sda", 156L);
        AddTargetResponse addTargetResponse = addTargetService.execute(addTargetRequest1);
        Assert.assertFalse(addTargetResponse.hasErrors());
        addTargetResponse = addTargetService.execute(addTargetRequest2);
        Assert.assertFalse(addTargetResponse.hasErrors());
        addTargetResponse = addTargetService.execute(addTargetRequest3);
        Assert.assertFalse(addTargetResponse.hasErrors());
        addTargetResponse = addTargetService.execute(addTargetRequest4);
        Assert.assertFalse(addTargetResponse.hasErrors());
    }

    private void createServices() {
        applicationContext = createApplicationContext();
        addTargetService = createAddTargetService();
        getAllTargetsService = createGetAllTargetService();
        databaseCleaner = createDatabaseCleaner();
        changeTargetNameService = createChangeTargetNameService();
        changeTargetDescriptionService = createChangeTargetDescriptionService();
        changeTargetDeadlineService = createChangeTargetDeadlineService();
        searchTargetByNameService = createSearchTargetByNameService();
        searchTargetByDescriptionService = createSearchTargetByDescriptionService();
        deleteTargetService = createDeleteTargetService();
    }

    private SearchTargetByDescriptionService createSearchTargetByDescriptionService() {
        return applicationContext.getBean(SearchTargetByDescriptionService.class);
    }

    private ChangeTargetNameService createChangeTargetNameService() {
        return applicationContext.getBean(ChangeTargetNameService.class);
    }

    private ChangeTargetDescriptionService createChangeTargetDescriptionService() {
        return applicationContext.getBean(ChangeTargetDescriptionService.class);
    }

    private ChangeTargetDeadlineService createChangeTargetDeadlineService() {
        return applicationContext.getBean(ChangeTargetDeadlineService.class);
    }

    private SearchTargetByNameService createSearchTargetByNameService() {
        return applicationContext.getBean(SearchTargetByNameService.class);
    }

    private DeleteTargetService createDeleteTargetService() {
        return applicationContext.getBean(DeleteTargetService.class);
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
