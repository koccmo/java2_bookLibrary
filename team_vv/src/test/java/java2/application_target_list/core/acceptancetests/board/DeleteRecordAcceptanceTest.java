package java2.application_target_list.core.acceptancetests.board;

import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.database.target.TargetRepository;
import java2.application_target_list.core.database.user.UserRepository;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.requests.board.DeleteRecordRequest;
import java2.application_target_list.core.requests.board.GetAllRecordsRequest;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.responses.board.DeleteRecordResponse;
import java2.application_target_list.core.responses.board.GetAllRecordsResponse;
import java2.application_target_list.core.services.board.AddRecordService;
import java2.application_target_list.core.services.board.DeleteRecordService;
import java2.application_target_list.core.services.board.GetAllRecordsService;
import java2.application_target_list.core.services.target.AddTargetService;
import java2.application_target_list.core.services.user.AddUserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class DeleteRecordAcceptanceTest {

    private DeleteRecordService deleteRecordService;
    private GetAllRecordsService getAllRecordsService;
    private ApplicationContext applicationContext;
    private AddRecordService addRecordService;
    private UserRepository userRepository;
    private TargetRepository targetRepository;
    private DatabaseCleaner databaseCleaner;
    private AddTargetService addTargetService;
    private AddUserService addUserService;
    private Long firstTargetId;
    private Long secondTargetId;
    private Long userId;

    @Before
    public void setup(){
        createServices();
        databaseCleaner.clean();
        addUsersToDB();
        addTargetsToDB();
        addRecordsToDB();
    }

    @Test
    public void shouldDeleteRecordFromList_v1() {
        Long recordId = getAllRecordsService.execute(new GetAllRecordsRequest()).getRecordList().get(0).getRecordId();
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(recordId);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());

        Assert.assertFalse(deleteRecordResponse.hasErrors());
        Assert.assertEquals(getAllRecordsResponse.getRecordList().size(), 1);
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(secondTargetId));
        Assert.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(userId));
    }

    @Test
    public void shouldDeleteRecordFromList_v2() {
        Long recordId = getAllRecordsService.execute(new GetAllRecordsRequest()).getRecordList().get(1).getRecordId();
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(recordId);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());

        Assert.assertFalse(deleteRecordResponse.hasErrors());
        Assert.assertEquals(getAllRecordsResponse.getRecordList().size(), 1);
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(firstTargetId));
        Assert.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(userId));
    }

    @Test
    public void shouldReturnErrorsList() {
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(-3L);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        Assert.assertTrue(deleteRecordResponse.hasErrors());
        Assert.assertEquals(deleteRecordResponse.getErrorList().size(), 2);
        Assert.assertEquals(deleteRecordResponse.getErrorList().get(0).getField(), "Record ID");
        Assert.assertEquals(deleteRecordResponse.getErrorList().get(0).getMessage(), "no record with that ID");
        Assert.assertEquals(deleteRecordResponse.getErrorList().get(1).getField(), "Record ID");
        Assert.assertEquals(deleteRecordResponse.getErrorList().get(1).getMessage(), "must not be negative!");

    }

    private void addRecordsToDB() {
        firstTargetId = targetRepository.getTargetsList().get(0).getId();
        secondTargetId = targetRepository.getTargetsList().get(1).getId();
        userId = userRepository.getUsersList().get(0).getId();

        AddRecordRequest addRecordRequest1 = new AddRecordRequest(firstTargetId, userId);
        AddRecordRequest addRecordRequest2 = new AddRecordRequest(secondTargetId, userId);
        addRecordService.execute(addRecordRequest1);
        addRecordService.execute(addRecordRequest2);
    }

    private void addUsersToDB() {
        AddUserRequest addUserRequest = new AddUserRequest("name1", "surname1");
        addUserService.execute(addUserRequest);
    }

    private void addTargetsToDB() {
        AddTargetRequest addTargetRequest1 = new AddTargetRequest("name", "description", 1L);
        AddTargetRequest addTargetRequest2 = new AddTargetRequest("name2", "description2", 2L);
        addTargetService.execute(addTargetRequest1);
        addTargetService.execute(addTargetRequest2);
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
        deleteRecordService = createDeleteRecordService();
    }

    private DeleteRecordService createDeleteRecordService() {
        return applicationContext.getBean(DeleteRecordService.class);
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
