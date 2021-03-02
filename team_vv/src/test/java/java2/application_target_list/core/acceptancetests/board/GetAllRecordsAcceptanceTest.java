package java2.application_target_list.core.acceptancetests.board;

import java2.application_target_list.core.DatabaseCleaner;
import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.database.jpa.JpaUserRepository;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.requests.board.GetAllRecordsRequest;
import java2.application_target_list.core.requests.target.AddTargetRequest;
import java2.application_target_list.core.requests.user.AddUserRequest;
import java2.application_target_list.core.responses.board.GetAllRecordsResponse;
import java2.application_target_list.core.services.board.AddRecordService;
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
public class GetAllRecordsAcceptanceTest {

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
    private AddTargetService addTargetService;
    @Autowired
    private AddUserService addUserService;

    @BeforeEach
    public void setup() {
        databaseCleaner.clean();
        addTargetsToDB();
        addUsersToDB();
    }

    @Test
    public void shouldReturnRecordsList() {
        Long targetId = jpaTargetRepository.findAll().get(0).getId();
        Long userId = jpaUserRepository.findAll().get(0).getId();

        AddRecordRequest addRecordRequest = new AddRecordRequest(targetId, userId);
        addRecordService.execute(addRecordRequest);
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());

        Assertions.assertFalse(getAllRecordsResponse.hasErrors());
        Assertions.assertEquals(getAllRecordsResponse.getRecordList().size(), 1);
        Assertions.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(targetId));
        Assertions.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(userId));
    }

    private void addUsersToDB() {
        AddUserRequest addUserRequest = new AddUserRequest("name1", "surname1");
        addUserService.execute(addUserRequest);
    }

    private void addTargetsToDB() {
        AddTargetRequest addTargetRequest = new AddTargetRequest("name", "description", 1L);
        addTargetService.execute(addTargetRequest);
    }
}
