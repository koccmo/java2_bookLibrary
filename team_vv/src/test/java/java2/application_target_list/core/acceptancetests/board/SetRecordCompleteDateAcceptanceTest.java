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
import java2.application_target_list.core.requests.board.SetRecordCompleteDateRequest;
import java2.application_target_list.core.responses.board.GetAllRecordsResponse;
import java2.application_target_list.core.responses.board.SetRecordCompleteDateResponse;
import java2.application_target_list.core.services.board.AddRecordService;
import java2.application_target_list.core.services.board.GetAllRecordsService;
import java2.application_target_list.core.services.board.SetRecordCompleteDateService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SetRecordCompleteDateAcceptanceTest {

    private GetAllRecordsService getAllRecordsService;
    private SetRecordCompleteDateService setRecordCompleteDateService;

    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
        AddRecordService addRecordService = applicationContext.getBean(AddRecordService.class);
        getAllRecordsService = applicationContext.getBean(GetAllRecordsService.class);
        setRecordCompleteDateService = applicationContext.getBean(SetRecordCompleteDateService.class);
        UserDatabase userDatabase = applicationContext.getBean(UserListImpl.class);
        TargetDatabase targetDatabase = applicationContext.getBean(TargetListImpl.class);
        userDatabase.addUser(new User("name1", "surname1"));
        targetDatabase.addTarget(new Target("name", "description", 1));
        AddRecordRequest addRecordRequest1 = new AddRecordRequest(1L, 1L);
        addRecordService.execute(addRecordRequest1);
    }

    @Test
    public void shouldSetRecordCompleteDate() {
        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(1L);
        SetRecordCompleteDateResponse setRecordCompleteDateResponse = setRecordCompleteDateService.execute(setRecordCompleteDateRequest);
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());
        Assert.assertFalse(setRecordCompleteDateResponse.hasErrors());
        Assert.assertNotNull(getAllRecordsResponse.getRecordList().get(0).getDateComplete());
    }

    @Test
    public void shouldReturnErrorList() {
        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(-1L);
        SetRecordCompleteDateResponse setRecordCompleteDateResponse = setRecordCompleteDateService.execute(setRecordCompleteDateRequest);
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());
        Assert.assertTrue(setRecordCompleteDateResponse.hasErrors());
        Assert.assertNull(getAllRecordsResponse.getRecordList().get(0).getDateComplete());
        Assert.assertEquals(setRecordCompleteDateResponse.getErrorList().size(), 2);
        Assert.assertEquals(setRecordCompleteDateResponse.getErrorList().get(0).getField(), "Record ID");
        Assert.assertEquals(setRecordCompleteDateResponse.getErrorList().get(0).getMessage(), "no record with that ID");
        Assert.assertEquals(setRecordCompleteDateResponse.getErrorList().get(1).getField(), "Record ID");
        Assert.assertEquals(setRecordCompleteDateResponse.getErrorList().get(1).getMessage(), "must not be negative!");
    }
}
