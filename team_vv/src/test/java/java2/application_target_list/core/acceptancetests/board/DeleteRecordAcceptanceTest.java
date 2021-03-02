package java2.application_target_list.core.acceptancetests.board;

import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.database.jpa.JpaUserRepository;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

@SpringBootTest
public class DeleteRecordAcceptanceTest {

    @Autowired
    private DeleteRecordService deleteRecordService;
    @Autowired
    private GetAllRecordsService getAllRecordsService;
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

    private Long firstTargetId;
    private Long secondTargetId;
    private Long userId;

    @BeforeEach
    public void setup(){
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

        Assertions.assertFalse(deleteRecordResponse.hasErrors());
        Assertions.assertEquals(getAllRecordsResponse.getRecordList().size(), 1);
        Assertions.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(secondTargetId));
        Assertions.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(userId));
    }

    @Test
    public void shouldDeleteRecordFromList_v2() {
        Long recordId = getAllRecordsService.execute(new GetAllRecordsRequest()).getRecordList().get(1).getRecordId();
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(recordId);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());

        Assertions.assertFalse(deleteRecordResponse.hasErrors());
        Assertions.assertEquals(getAllRecordsResponse.getRecordList().size(), 1);
        Assertions.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(firstTargetId));
        Assertions.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(userId));
    }

    @Test
    public void shouldReturnErrorsList() {
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(-3L);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        Assertions.assertTrue(deleteRecordResponse.hasErrors());
        Assertions.assertEquals(deleteRecordResponse.getErrorList().size(), 2);
        Assertions.assertEquals(deleteRecordResponse.getErrorList().get(1).getField(), "Record ID");
        Assertions.assertEquals(deleteRecordResponse.getErrorList().get(1).getMessage(), "no record with that ID");
        Assertions.assertEquals(deleteRecordResponse.getErrorList().get(0).getField(), "Record ID");
        Assertions.assertEquals(deleteRecordResponse.getErrorList().get(0).getMessage(), "must not be negative!");

    }

    private void addRecordsToDB() {
        firstTargetId = jpaTargetRepository.findAll().get(0).getId();
        secondTargetId = jpaTargetRepository.findAll().get(1).getId();
        userId = jpaUserRepository.findAll().get(0).getId();

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
}
