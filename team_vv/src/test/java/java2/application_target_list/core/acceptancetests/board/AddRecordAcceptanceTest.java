package java2.application_target_list.core.acceptancetests.board;

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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;


@SpringBootTest
public class AddRecordAcceptanceTest {

    @Autowired
    private AddRecordService addRecordService;
    @Autowired
    private GetAllRecordsService getAllRecordsService;
    @Autowired
    private JpaTargetRepository jpaTargetRepository;
    @Autowired
    private JpaUserRepository jpaUserRepository;
    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private AddUserService addUserService;
    @Autowired
    private AddTargetService addTargetService;


    @BeforeEach
    public void setup(){
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

        Assertions.assertFalse(addRecordResponse1.hasErrors());
        Assertions.assertFalse(addRecordResponse2.hasErrors());
        Assertions.assertFalse(getAllRecordsResponse.hasErrors());
        Assertions.assertEquals(getAllRecordsResponse.getRecordList().size(), 2);
        Assertions.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(targetId));
        Assertions.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(firstUserId));
        Assertions.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getTargetId()), Optional.of(targetId));
        Assertions.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getUserId()), Optional.of(secondUserId));
    }

    @Test
    public void shouldReturnErrorList() {
        Long firstUserId = jpaUserRepository.findAll().get(0).getId();
        AddRecordRequest addRecordRequest1 = new AddRecordRequest(-1L, firstUserId);
        AddRecordResponse addRecordResponse = addRecordService.execute(addRecordRequest1);

        Assertions.assertTrue(addRecordResponse.hasErrors());
        Assertions.assertEquals(addRecordResponse.getErrorList().size(), 2);
        Assertions.assertEquals(addRecordResponse.getErrorList().get(0).getField(), "Target ID");
        Assertions.assertEquals(addRecordResponse.getErrorList().get(0).getMessage(), "must not be negative!");
        Assertions.assertEquals(addRecordResponse.getErrorList().get(1).getField(), "Target ID");
        Assertions.assertEquals(addRecordResponse.getErrorList().get(1).getMessage(), "no target with that ID!");
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
}
