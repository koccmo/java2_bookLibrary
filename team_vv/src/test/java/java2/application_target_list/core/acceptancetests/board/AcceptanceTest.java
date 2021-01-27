package java2.application_target_list.core.acceptancetests.board;

import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.database.target.TargetRepository;
import java2.application_target_list.core.database.user.UserRepository;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.requests.board.DeleteRecordRequest;
import java2.application_target_list.core.requests.board.GetAllRecordsRequest;
import java2.application_target_list.core.requests.board.SetRecordCompleteDateRequest;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.responses.board.AddRecordResponse;
import java2.application_target_list.core.responses.board.DeleteRecordResponse;
import java2.application_target_list.core.responses.board.GetAllRecordsResponse;
import java2.application_target_list.core.responses.board.SetRecordCompleteDateResponse;
import java2.application_target_list.core.services.board.AddRecordService;
import java2.application_target_list.core.services.board.DeleteRecordService;
import java2.application_target_list.core.services.board.GetAllRecordsService;
import java2.application_target_list.core.services.board.SetRecordCompleteDateService;
import java2.application_target_list.core.services.target.AddTargetService;
import java2.application_target_list.core.services.user.AddUserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AcceptanceTest {

    private GetAllRecordsService getAllRecordsService;
    private SetRecordCompleteDateService setRecordCompleteDateService;
    private DeleteRecordService deleteRecordService;
    private AddRecordService addRecordService;
    private ApplicationContext applicationContext;
    private TargetRepository targetRepository;
    private UserRepository userRepository;
    private DatabaseCleaner databaseCleaner;
    private AddTargetService addTargetService;
    private AddUserService addUserService;
    private Long targetId;
    private Long userId;
    private Long recordId;

    @Before
    public void setup() {
        createServices();
        databaseCleaner.clean();
        addUsersToDB();
        addTargetsToDB();
    }

    @Test
    public void acceptanceTest() {
        addRecordsToDB();
        deleteRecordFromDB();
        setRecordCompleteDate();
        checkChanges();
    }

    private void checkChanges() {
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());
        Assert.assertFalse(getAllRecordsResponse.hasErrors());

        targetId = getAllRecordsResponse.getRecordList().get(0).getTargetId();
        userId = getAllRecordsResponse.getRecordList().get(0).getUserId();
        Assert.assertEquals(getAllRecordsResponse.getRecordList().size(), 3);
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(targetId));
        Assert.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(userId));
        Assert.assertNotNull(getAllRecordsResponse.getRecordList().get(0).getDateAdded());
        Assert.assertNotNull(getAllRecordsResponse.getRecordList().get(0).getDateComplete());

        targetId = getAllRecordsResponse.getRecordList().get(1).getTargetId();
        userId = getAllRecordsResponse.getRecordList().get(1).getUserId();
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getTargetId()), Optional.of(targetId));
        Assert.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getUserId()), Optional.of(userId));
        Assert.assertNotNull(getAllRecordsResponse.getRecordList().get(1).getDateAdded());
        Assert.assertNull(getAllRecordsResponse.getRecordList().get(1).getDateComplete());

        targetId = getAllRecordsResponse.getRecordList().get(2).getTargetId();
        userId = getAllRecordsResponse.getRecordList().get(2).getUserId();
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(2).getTargetId()), Optional.of(targetId));
        Assert.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(2).getUserId()), Optional.of(userId));
        Assert.assertNotNull(getAllRecordsResponse.getRecordList().get(2).getDateAdded());
        Assert.assertNotNull(getAllRecordsResponse.getRecordList().get(2).getDateComplete());
    }

    private void setRecordCompleteDate() {
        recordId = getAllRecordsService.execute(new GetAllRecordsRequest()).getRecordList().get(0).getRecordId();
        SetRecordCompleteDateRequest setRecordCompleteDateRequest1 = new SetRecordCompleteDateRequest(recordId);
        SetRecordCompleteDateResponse setRecordCompleteDateResponse1 = setRecordCompleteDateService.execute(setRecordCompleteDateRequest1);
        Assert.assertFalse(setRecordCompleteDateResponse1.hasErrors());

        recordId = getAllRecordsService.execute(new GetAllRecordsRequest()).getRecordList().get(2).getRecordId();
        SetRecordCompleteDateRequest setRecordCompleteDateRequest2 = new SetRecordCompleteDateRequest(recordId);
        SetRecordCompleteDateResponse setRecordCompleteDateResponse2 = setRecordCompleteDateService.execute(setRecordCompleteDateRequest2);
        Assert.assertFalse(setRecordCompleteDateResponse2.hasErrors());
    }

    private void deleteRecordFromDB() {
        recordId = getAllRecordsService.execute(new GetAllRecordsRequest()).getRecordList().get(2).getRecordId();
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(recordId);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        GetAllRecordsRequest getAllRecordsRequest = new GetAllRecordsRequest();
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(getAllRecordsRequest);

        Assert.assertFalse(deleteRecordResponse.hasErrors());
        Assert.assertEquals(getAllRecordsResponse.getRecordList().size(), 3);
    }

    private void addRecordsToDB() {
        targetId = targetRepository.getTargetsList().get(0).getId();
        userId = userRepository.getUsersList().get(0).getId();
        AddRecordRequest addRecordRequest1 = new AddRecordRequest(targetId, userId);
        AddRecordResponse addRecordResponse1 = addRecordService.execute(addRecordRequest1);
        Assert.assertFalse(addRecordResponse1.hasErrors());

        targetId = targetRepository.getTargetsList().get(1).getId();
        userId = userRepository.getUsersList().get(1).getId();
        AddRecordRequest addRecordRequest2 = new AddRecordRequest(targetId, userId);
        AddRecordResponse addRecordResponse2 = addRecordService.execute(addRecordRequest2);
        Assert.assertFalse(addRecordResponse2.hasErrors());

        targetId = targetRepository.getTargetsList().get(2).getId();
        userId = userRepository.getUsersList().get(0).getId();
        AddRecordRequest addRecordRequest3 = new AddRecordRequest(targetId, userId);
        AddRecordResponse addRecordResponse3 = addRecordService.execute(addRecordRequest3);
        Assert.assertFalse(addRecordResponse3.hasErrors());

        targetId = targetRepository.getTargetsList().get(3).getId();
        userId = userRepository.getUsersList().get(2).getId();
        AddRecordRequest addRecordRequest4 = new AddRecordRequest(targetId, userId);
        AddRecordResponse addRecordResponse4 = addRecordService.execute(addRecordRequest4);
        Assert.assertFalse(addRecordResponse4.hasErrors());
    }

    private void addUsersToDB() {
        AddUserRequest addUserRequest1 = new AddUserRequest("name1", "surname1");
        AddUserRequest addUserRequest2 = new AddUserRequest("name2", "surname2");
        AddUserRequest addUserRequest3 = new AddUserRequest("name3", "surname3");
        addUserService.execute(addUserRequest1);
        addUserService.execute(addUserRequest2);
        addUserService.execute(addUserRequest3);
    }

    private void addTargetsToDB() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name1", "description1", 1L);
        AddTargetRequest addTargetRequest2 = new AddTargetRequest("name2", "description2", 2L);
        AddTargetRequest addTargetRequest3 = new AddTargetRequest("name3", "description3", 3L);
        AddTargetRequest addTargetRequest4 = new AddTargetRequest("name4", "description4", 4L);
        addTargetService.execute(addTargetRequest1);
        addTargetService.execute(addTargetRequest2);
        addTargetService.execute(addTargetRequest3);
        addTargetService.execute(addTargetRequest4);
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
        setRecordCompleteDateService = createSetRecordCompleteDateService();
        deleteRecordService = createDeleteRecordService();
    }

    private DeleteRecordService createDeleteRecordService() {
        return applicationContext.getBean(DeleteRecordService.class);
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
        return new AnnotationConfigApplicationContext(TargetListConfiguration.class);
    }
}
