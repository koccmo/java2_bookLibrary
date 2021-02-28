package java2.application_target_list.core.acceptancetests.board;

import java2.application_target_list.config.SpringCoreConfiguration;
import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.requests.board.GetAllRecordsRequest;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.responses.board.AddRecordResponse;
import java2.application_target_list.core.responses.board.GetAllRecordsResponse;
import java2.application_target_list.core.services.board.AddRecordService;
import java2.application_target_list.core.services.board.GetAllRecordsService;
import java2.application_target_list.core.services.target.AddTargetService;
import java2.application_target_list.core.services.user.AddUserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AddRecordAcceptanceTest {

    private AddRecordService addRecordService;
    private GetAllRecordsService getAllRecordsService;
    private ApplicationContext applicationContext;
    private JpaTargetRepository jpaTargetRepository;
    private JpaUserRepository jpaUserRepository;
    private DatabaseCleaner databaseCleaner;
    private AddUserService addUserService;
    private AddTargetService addTargetService;


    @Before
    public void setup(){
        createServices();
        databaseCleaner.clean();
        addUsersToDB();
        addTargetsToDB();
    }

    @Test
    public void shouldAddRecordToList() {
        Long targetId = jpaTargetRepository.findAll().get(0).getId();
        Long firstUserId = jpaUserRepository.findAll().get(0).getId();
        Long secondUserId = jpaUserRepository.findAll().get(1).getId();

        AddRecordRequest addRecordRequest1 = new AddRecordRequest(targetId, firstUserId);
        AddRecordRequest addRecordRequest2 = new AddRecordRequest(targetId, secondUserId);
        AddRecordResponse addRecordResponse1 = addRecordService.execute(addRecordRequest1);
        AddRecordResponse addRecordResponse2 = addRecordService.execute(addRecordRequest2);
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());

        Assert.assertFalse(addRecordResponse1.hasErrors());
        Assert.assertFalse(addRecordResponse2.hasErrors());
        Assert.assertFalse(getAllRecordsResponse.hasErrors());
        Assert.assertEquals(getAllRecordsResponse.getRecordList().size(), 2);
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(targetId));
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(firstUserId));
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getTargetId()), Optional.of(targetId));
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getUserId()), Optional.of(secondUserId));
    }

    @Test
    public void shouldReturnErrorList() {
        Long firstUserId = jpaUserRepository.findAll().get(0).getId();
        AddRecordRequest addRecordRequest1 = new AddRecordRequest(-1L, firstUserId);
        AddRecordResponse addRecordResponse = addRecordService.execute(addRecordRequest1);

        Assert.assertTrue(addRecordResponse.hasErrors());
        Assert.assertEquals(addRecordResponse.getErrorList().size(), 2);
        Assert.assertEquals(addRecordResponse.getErrorList().get(0).getField(), "Target ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(0).getMessage(), "must not be negative!");
        Assert.assertEquals(addRecordResponse.getErrorList().get(1).getField(), "Target ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(1).getMessage(), "no target with that ID!");
    }

    private void addUsersToDB(){
        AddUserRequest addUserRequest1 = new AddUserRequest("name1", "surname1");
        AddUserRequest addUserRequest2 = new AddUserRequest("name2", "surname2");
        addUserService.execute(addUserRequest1);
        addUserService.execute(addUserRequest2);
    }

    private void addTargetsToDB() {
        AddTargetRequest addTargetRequest = new AddTargetRequest("name", "description", 1L);
        addTargetService.execute(addTargetRequest);
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
