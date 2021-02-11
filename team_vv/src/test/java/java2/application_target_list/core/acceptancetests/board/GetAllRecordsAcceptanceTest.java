package java2.application_target_list.core.acceptancetests.board;

import java2.application_target_list.config.SpringCoreConfiguration;
import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.database.target.TargetRepository;
import java2.application_target_list.core.database.user.UserRepository;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.requests.board.GetAllRecordsRequest;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.user.AddUserRequest;
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

public class GetAllRecordsAcceptanceTest {

    private AddRecordService addRecordService;
    private GetAllRecordsService getAllRecordsService;
    private ApplicationContext applicationContext;
    private UserRepository userRepository;
    private TargetRepository targetRepository;
    private DatabaseCleaner databaseCleaner;
    private AddTargetService addTargetService;
    private AddUserService addUserService;

    @Before
    public void setup() {
        createServices();
        databaseCleaner.clean();
        addTargetsToDB();
        addUsersToDB();
    }

    @Test
    public void shouldReturnRecordsList() {
        Long targetId = targetRepository.getTargetsList().get(0).getId();
        Long userId = userRepository.getUsersList().get(0).getId();

        AddRecordRequest addRecordRequest = new AddRecordRequest(targetId, userId);
        addRecordService.execute(addRecordRequest);
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());

        Assert.assertFalse(getAllRecordsResponse.hasErrors());
        Assert.assertEquals(getAllRecordsResponse.getRecordList().size(), 1);
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(targetId));
        Assert.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(userId));
    }

    private void addUsersToDB() {
        AddUserRequest addUserRequest = new AddUserRequest("name1", "surname1");
        addUserService.execute(addUserRequest);
    }

    private void addTargetsToDB() {
        AddTargetRequest addTargetRequest = new AddTargetRequest("name", "description", 1L);
        addTargetService.execute(addTargetRequest);
    }

    private void createServices() {
        applicationContext = createApplicationContext();
        addRecordService = createAddRecordService();
        getAllRecordsService = createGetAllRecordsService();
        userRepository = createUserRepository();
        targetRepository = createTargetRepository();
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

    private TargetRepository createTargetRepository() {
        return applicationContext.getBean(TargetRepository.class);
    }

    private UserRepository createUserRepository() {
        return applicationContext.getBean(UserRepository.class);
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
