package java2.application_target_list.core.acceptancetests.board;

import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.database.target.TargetDatabase;
import java2.application_target_list.core.database.target.TargetListImpl;
import java2.application_target_list.core.database.user.UserDatabase;
import java2.application_target_list.core.database.user.UserListImpl;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.requests.board.GetAllRecordsRequest;
import java2.application_target_list.core.responses.board.GetAllRecordsResponse;
import java2.application_target_list.core.services.board.AddRecordService;
import java2.application_target_list.core.services.board.GetAllRecordsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class GetAllRecordsAcceptanceTest {

    private AddRecordService addRecordService;
    private GetAllRecordsService getAllRecordsService;

    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
        addRecordService = applicationContext.getBean(AddRecordService.class);
        getAllRecordsService = applicationContext.getBean(GetAllRecordsService.class);
        UserDatabase userDatabase = applicationContext.getBean(UserListImpl.class);
        TargetDatabase targetDatabase = applicationContext.getBean(TargetListImpl.class);
        userDatabase.addUser(new User("name1", "surname1"));
        targetDatabase.addTarget(new Target("name", "description", 1));
    }

    @Test
    public void shouldReturnRecordsList() {
        AddRecordRequest addRecordRequest = new AddRecordRequest(1L, 1L);
        addRecordService.execute(addRecordRequest);
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());
        Assert.assertFalse(getAllRecordsResponse.hasErrors());
        Assert.assertEquals(getAllRecordsResponse.getRecordList().size(), 1);
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(1L));
        Assert.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(1L));
    }
}
