package java2.application_target_list.core.acceptancetests.board;

import java2.application_target_list.config.SpringCoreConfiguration;
import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.requests.board.GetAllRecordsRequest;
import java2.application_target_list.core.requests.board.SetRecordCompleteDateRequest;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.responses.board.GetAllRecordsResponse;
import java2.application_target_list.core.responses.board.SetRecordCompleteDateResponse;
import java2.application_target_list.core.services.board.AddRecordService;
import java2.application_target_list.core.services.board.GetAllRecordsService;
import java2.application_target_list.core.services.board.SetRecordCompleteDateService;
import java2.application_target_list.core.services.target.AddTargetService;
import java2.application_target_list.core.services.user.AddUserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SetRecordCompleteDateAcceptanceTest {

    private GetAllRecordsService getAllRecordsService;
    private SetRecordCompleteDateService setRecordCompleteDateService;
    private ApplicationContext applicationContext;
    private JpaTargetRepository jpaTargetRepository;
    private JpaUserRepository jpaUserRepository;
    private DatabaseCleaner databaseCleaner;
    private AddTargetService addTargetService;
    private AddUserService addUserService;
    private AddRecordService addRecordService;
    private Long userId;
    private Long targetId;
    private Long recordId;

    @Before
    public void setup() {
        createServices();
        databaseCleaner.clean();
        addTargetsToDB();
        addUsersToDB();
        addRecordsToDB();
    }

    @Test
    public void shouldSetRecordCompleteDate() {
        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(recordId);
        SetRecordCompleteDateResponse setRecordCompleteDateResponse = setRecordCompleteDateService.execute(setRecordCompleteDateRequest);
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());

        Assert.assertFalse(setRecordCompleteDateResponse.hasErrors());
        Assert.assertNotNull(getAllRecordsResponse.getRecordList().get(0).getDateComplete());
    }

    @Test
    public void shouldReturnErrorList() {
        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(-1L);
        SetRecordCompleteDateResponse setRecordCompleteDateResponse = setRecordCompleteDateService.execute(setRecordCompleteDateRequest);
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());

        Assert.assertTrue(setRecordCompleteDateResponse.hasErrors());
        Assert.assertNull(getAllRecordsResponse.getRecordList().get(0).getDateComplete());
        Assert.assertEquals(setRecordCompleteDateResponse.getErrorList().size(), 2);
        Assert.assertEquals(setRecordCompleteDateResponse.getErrorList().get(1).getField(), "Record ID");
        Assert.assertEquals(setRecordCompleteDateResponse.getErrorList().get(1).getMessage(), "no record with that ID");
        Assert.assertEquals(setRecordCompleteDateResponse.getErrorList().get(0).getField(), "Record ID");
        Assert.assertEquals(setRecordCompleteDateResponse.getErrorList().get(0).getMessage(), "must not be negative!");
    }

    private void addRecordsToDB() {
        AddRecordRequest addRecordRequest1 = new AddRecordRequest(targetId, userId);
        addRecordService.execute(addRecordRequest1);
        recordId = getAllRecordsService.execute(new GetAllRecordsRequest()).getRecordList().get(0).getRecordId();
    }

    private void addUsersToDB() {
        AddUserRequest addUserRequest = new AddUserRequest("name1", "surname1");
        addUserService.execute(addUserRequest);
        userId = jpaUserRepository.findAll().get(0).getId();
    }

    private void addTargetsToDB() {
        AddTargetRequest addTargetRequest = new AddTargetRequest("name", "description", 1L);
        addTargetService.execute(addTargetRequest);
        targetId = jpaTargetRepository.findAll().get(0).getId();
    }

    private void createServices() {
        applicationContext = createApplicationContext();
        addRecordService = createAddRecordService();
        getAllRecordsService = createGetAllRecordsService();
        jpaTargetRepository = createJpaTargetRepository();
        jpaUserRepository = createJpaUserRepository();
        databaseCleaner = createDatabaseCleaner();
        addTargetService = createAddTargetService();
        addUserService = createAddUserService();
        setRecordCompleteDateService = createSetRecordCompleteDateService();
    }

    private SetRecordCompleteDateService createSetRecordCompleteDateService() {
        return applicationContext.getBean(SetRecordCompleteDateService.class);
    }

    private AddUserService createAddUserService() {
        return applicationContext.getBean(AddUserService.class);
    }

    private AddTargetService createAddTargetService() {
        return applicationContext.getBean(AddTargetService.class);
    }

    private DatabaseCleaner createDatabaseCleaner() {
        return applicationContext.getBean(DatabaseCleaner.class);
    }

    private JpaTargetRepository createJpaTargetRepository() {
        return applicationContext.getBean(JpaTargetRepository.class);
    }

    private JpaUserRepository createJpaUserRepository() {
        return applicationContext.getBean(JpaUserRepository.class);
    }

    private GetAllRecordsService createGetAllRecordsService() {
        return applicationContext.getBean(GetAllRecordsService.class);
    }

    private AddRecordService createAddRecordService() {
        return applicationContext.getBean(AddRecordService.class);
    }

    private ApplicationContext createApplicationContext() {
        return new AnnotationConfigApplicationContext(SpringCoreConfiguration.class);
    }
}
