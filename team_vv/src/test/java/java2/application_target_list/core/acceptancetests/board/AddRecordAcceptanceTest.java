package java2.application_target_list.core.acceptancetests.board;

import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.database.TargetDatabase;
import java2.application_target_list.core.database.TargetListImpl;
import java2.application_target_list.core.database.UserDatabase;
import java2.application_target_list.core.database.UserListImpl;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.requests.board.GetAllRecordsRequest;
import java2.application_target_list.core.responses.board.AddRecordResponse;
import java2.application_target_list.core.responses.board.GetAllRecordsResponse;
import java2.application_target_list.core.services.board.AddRecordService;
import java2.application_target_list.core.services.board.GetAllRecordsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AddRecordAcceptanceTest {

    private AddRecordService addRecordService;
    private GetAllRecordsService getAllRecordsService;

    @Before
    public void setup(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
        addRecordService = applicationContext.getBean(AddRecordService.class);
        getAllRecordsService = applicationContext.getBean(GetAllRecordsService.class);
        UserDatabase userDatabase = applicationContext.getBean(UserListImpl.class);
        TargetDatabase targetDatabase = applicationContext.getBean(TargetListImpl.class);
        userDatabase.addUser(new User("name1", "surname1"));
        userDatabase.addUser(new User("name2", "surname2"));
        targetDatabase.addTarget(new Target("name", "description", 1));

    }

    @Test
    public void shouldAddRecordToList() {
        AddRecordRequest addRecordRequest1 = new AddRecordRequest(1L, 1L);
        AddRecordRequest addRecordRequest2 = new AddRecordRequest(1L, 2L);
        addRecordService.execute(addRecordRequest1);
        addRecordService.execute(addRecordRequest2);
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());
        Assert.assertFalse(getAllRecordsResponse.hasErrors());
        Assert.assertEquals(getAllRecordsResponse.getRecordList().size(), 2);
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(1L));
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(1L));
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getTargetId()), Optional.of(1L));
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getUserId()), Optional.of(2L));
    }

    @Test
    public void shouldReturnErrorList() {
        AddRecordRequest addRecordRequest1 = new AddRecordRequest(-1L, 1L);
        AddRecordResponse addRecordResponse = addRecordService.execute(addRecordRequest1);
        Assert.assertTrue(addRecordResponse.hasErrors());
        Assert.assertEquals(addRecordResponse.getErrorList().size(), 2);
        Assert.assertEquals(addRecordResponse.getErrorList().get(0).getField(), "Target ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(0).getMessage(), "must not be negative!");
        Assert.assertEquals(addRecordResponse.getErrorList().get(1).getField(), "Target ID");
        Assert.assertEquals(addRecordResponse.getErrorList().get(1).getMessage(), "no target with that ID!");
    }
}
