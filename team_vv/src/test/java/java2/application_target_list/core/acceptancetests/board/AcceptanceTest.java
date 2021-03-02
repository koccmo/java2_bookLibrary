package java2.application_target_list.core.acceptancetests.board;

import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.database.jpa.JpaUserRepository;
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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

@SpringBootTest
public class AcceptanceTest {

    @Autowired
    private GetAllRecordsService getAllRecordsService;
    @Autowired
    private SetRecordCompleteDateService setRecordCompleteDateService;
    @Autowired
    private DeleteRecordService deleteRecordService;
    @Autowired
    private AddRecordService addRecordService;
    @Autowired
    private JpaTargetRepository jpaTargetRepository;
    @Autowired
    private JpaUserRepository jpaUserRepository;
    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private AddTargetService addTargetService;
    @Autowired
    private AddUserService addUserService;

    private Long targetId;
    private Long userId;
    private Long recordId;

    @BeforeEach
    public void setup() {
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
        Assertions.assertFalse(getAllRecordsResponse.hasErrors());

        targetId = getAllRecordsResponse.getRecordList().get(0).getTargetId();
        userId = getAllRecordsResponse.getRecordList().get(0).getUserId();
        Assertions.assertEquals(getAllRecordsResponse.getRecordList().size(), 3);
        Assertions.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(targetId));
        Assertions.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(userId));
        Assertions.assertNotNull(getAllRecordsResponse.getRecordList().get(0).getDateAdded());
        Assertions.assertNotNull(getAllRecordsResponse.getRecordList().get(0).getDateComplete());

        targetId = getAllRecordsResponse.getRecordList().get(1).getTargetId();
        userId = getAllRecordsResponse.getRecordList().get(1).getUserId();
        Assertions.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getTargetId()), Optional.of(targetId));
        Assertions.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getUserId()), Optional.of(userId));
        Assertions.assertNotNull(getAllRecordsResponse.getRecordList().get(1).getDateAdded());
        Assertions.assertNull(getAllRecordsResponse.getRecordList().get(1).getDateComplete());

        targetId = getAllRecordsResponse.getRecordList().get(2).getTargetId();
        userId = getAllRecordsResponse.getRecordList().get(2).getUserId();
        Assertions.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(2).getTargetId()), Optional.of(targetId));
        Assertions.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(2).getUserId()), Optional.of(userId));
        Assertions.assertNotNull(getAllRecordsResponse.getRecordList().get(2).getDateAdded());
        Assertions.assertNotNull(getAllRecordsResponse.getRecordList().get(2).getDateComplete());
    }

    private void setRecordCompleteDate() {
        recordId = getAllRecordsService.execute(new GetAllRecordsRequest()).getRecordList().get(0).getRecordId();
        SetRecordCompleteDateRequest setRecordCompleteDateRequest1 = new SetRecordCompleteDateRequest(recordId);
        SetRecordCompleteDateResponse setRecordCompleteDateResponse1 = setRecordCompleteDateService.execute(setRecordCompleteDateRequest1);
        Assertions.assertFalse(setRecordCompleteDateResponse1.hasErrors());

        recordId = getAllRecordsService.execute(new GetAllRecordsRequest()).getRecordList().get(2).getRecordId();
        SetRecordCompleteDateRequest setRecordCompleteDateRequest2 = new SetRecordCompleteDateRequest(recordId);
        SetRecordCompleteDateResponse setRecordCompleteDateResponse2 = setRecordCompleteDateService.execute(setRecordCompleteDateRequest2);
        Assertions.assertFalse(setRecordCompleteDateResponse2.hasErrors());
    }

    private void deleteRecordFromDB() {
        recordId = getAllRecordsService.execute(new GetAllRecordsRequest()).getRecordList().get(2).getRecordId();
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(recordId);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        GetAllRecordsRequest getAllRecordsRequest = new GetAllRecordsRequest();
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(getAllRecordsRequest);

        Assertions.assertFalse(deleteRecordResponse.hasErrors());
        Assertions.assertEquals(getAllRecordsResponse.getRecordList().size(), 3);
    }

    private void addRecordsToDB() {
        targetId = jpaTargetRepository.findAll().get(0).getId();
        userId = jpaUserRepository.findAll().get(0).getId();
        AddRecordRequest addRecordRequest1 = new AddRecordRequest(targetId, userId);
        AddRecordResponse addRecordResponse1 = addRecordService.execute(addRecordRequest1);
        Assertions.assertFalse(addRecordResponse1.hasErrors());

        targetId = jpaTargetRepository.findAll().get(1).getId();
        userId = jpaUserRepository.findAll().get(1).getId();
        AddRecordRequest addRecordRequest2 = new AddRecordRequest(targetId, userId);
        AddRecordResponse addRecordResponse2 = addRecordService.execute(addRecordRequest2);
        Assertions.assertFalse(addRecordResponse2.hasErrors());

        targetId = jpaTargetRepository.findAll().get(2).getId();
        userId = jpaUserRepository.findAll().get(0).getId();
        AddRecordRequest addRecordRequest3 = new AddRecordRequest(targetId, userId);
        AddRecordResponse addRecordResponse3 = addRecordService.execute(addRecordRequest3);
        Assertions.assertFalse(addRecordResponse3.hasErrors());

        targetId = jpaTargetRepository.findAll().get(3).getId();
        userId = jpaUserRepository.findAll().get(2).getId();
        AddRecordRequest addRecordRequest4 = new AddRecordRequest(targetId, userId);
        AddRecordResponse addRecordResponse4 = addRecordService.execute(addRecordRequest4);
        Assertions.assertFalse(addRecordResponse4.hasErrors());
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
}
