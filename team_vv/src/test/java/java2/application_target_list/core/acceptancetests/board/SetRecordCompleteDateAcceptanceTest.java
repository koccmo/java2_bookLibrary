package java2.application_target_list.core.acceptancetests.board;

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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
public class SetRecordCompleteDateAcceptanceTest {

    @Autowired
    private GetAllRecordsService getAllRecordsService;
    @Autowired
    private SetRecordCompleteDateService setRecordCompleteDateService;
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
    @Autowired
    private AddRecordService addRecordService;

    private Long userId;
    private Long targetId;
    private Long recordId;

    @BeforeEach
    public void setup() {
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

        Assertions.assertFalse(setRecordCompleteDateResponse.hasErrors());
        Assertions.assertNotNull(getAllRecordsResponse.getRecordList().get(0).getDateComplete());
    }

    @Test
    public void shouldReturnErrorList() {
        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(-1L);
        SetRecordCompleteDateResponse setRecordCompleteDateResponse = setRecordCompleteDateService.execute(setRecordCompleteDateRequest);
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());

        Assertions.assertTrue(setRecordCompleteDateResponse.hasErrors());
        Assertions.assertNull(getAllRecordsResponse.getRecordList().get(0).getDateComplete());
        Assertions.assertEquals(setRecordCompleteDateResponse.getErrorList().size(), 2);
        Assertions.assertEquals(setRecordCompleteDateResponse.getErrorList().get(1).getField(), "Record ID");
        Assertions.assertEquals(setRecordCompleteDateResponse.getErrorList().get(1).getMessage(), "no record with that ID");
        Assertions.assertEquals(setRecordCompleteDateResponse.getErrorList().get(0).getField(), "Record ID");
        Assertions.assertEquals(setRecordCompleteDateResponse.getErrorList().get(0).getMessage(), "must not be negative!");
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
}
