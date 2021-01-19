package java2.application_target_list.core.acceptancetests.board;

import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.database.target.TargetDatabase;
import java2.application_target_list.core.database.target.TargetListImpl;
import java2.application_target_list.core.database.user.UserDatabase;
import java2.application_target_list.core.database.user.UserListImpl;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.requests.board.DeleteRecordRequest;
import java2.application_target_list.core.requests.board.GetAllRecordsRequest;
import java2.application_target_list.core.requests.board.SetRecordCompleteDateRequest;
import java2.application_target_list.core.responses.board.AddRecordResponse;
import java2.application_target_list.core.responses.board.DeleteRecordResponse;
import java2.application_target_list.core.responses.board.GetAllRecordsResponse;
import java2.application_target_list.core.responses.board.SetRecordCompleteDateResponse;
import java2.application_target_list.core.services.board.AddRecordService;
import java2.application_target_list.core.services.board.DeleteRecordService;
import java2.application_target_list.core.services.board.GetAllRecordsService;
import java2.application_target_list.core.services.board.SetRecordCompleteDateService;
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
    private AddRecordResponse addRecordResponse;
    private SetRecordCompleteDateResponse setRecordCompleteDateResponse;

    @Before
    public void setup() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
        getAllRecordsService = applicationContext.getBean(GetAllRecordsService.class);
        setRecordCompleteDateService = applicationContext.getBean(SetRecordCompleteDateService.class);
        deleteRecordService = applicationContext.getBean(DeleteRecordService.class);
        addRecordService = applicationContext.getBean(AddRecordService.class);
        UserDatabase userDatabase = applicationContext.getBean(UserListImpl.class);
        TargetDatabase targetDatabase = applicationContext.getBean(TargetListImpl.class);
        userDatabase.addUser(new User("name1", "surname1"));
        userDatabase.addUser(new User("name2", "surname2"));
        userDatabase.addUser(new User("name3", "surname3"));
        targetDatabase.addTarget(new Target("name1", "description1", 1));
        targetDatabase.addTarget(new Target("name2", "description2", 2));
        targetDatabase.addTarget(new Target("name3", "description3", 3));
        targetDatabase.addTarget(new Target("name4", "description4", 4));
    }

    @Test
    public void acceptanceTest() {
        AddRecordRequest addRecordRequest1 = new AddRecordRequest(1L, 1L);
        AddRecordRequest addRecordRequest2 = new AddRecordRequest(2L, 2L);
        AddRecordRequest addRecordRequest3 = new AddRecordRequest(3L, 1L); //
        AddRecordRequest addRecordRequest4 = new AddRecordRequest(4L, 3L);
        addRecordResponse = addRecordService.execute(addRecordRequest1);
        Assert.assertFalse(addRecordResponse.hasErrors());
        addRecordResponse = addRecordService.execute(addRecordRequest2);
        Assert.assertFalse(addRecordResponse.hasErrors());
        addRecordResponse = addRecordService.execute(addRecordRequest3);
        Assert.assertFalse(addRecordResponse.hasErrors());
        addRecordResponse = addRecordService.execute(addRecordRequest4);
        Assert.assertFalse(addRecordResponse.hasErrors());

        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(3L);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        Assert.assertFalse(deleteRecordResponse.hasErrors());

        SetRecordCompleteDateRequest setRecordCompleteDateRequest1 = new SetRecordCompleteDateRequest(1L);
        SetRecordCompleteDateRequest setRecordCompleteDateRequest2 = new SetRecordCompleteDateRequest(4L);
        setRecordCompleteDateResponse = setRecordCompleteDateService.execute(setRecordCompleteDateRequest1);
        Assert.assertFalse(setRecordCompleteDateResponse.hasErrors());
        setRecordCompleteDateResponse = setRecordCompleteDateService.execute(setRecordCompleteDateRequest2);
        Assert.assertFalse(setRecordCompleteDateResponse.hasErrors());

        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());
        Assert.assertFalse(getAllRecordsResponse.hasErrors());

        Assert.assertEquals(getAllRecordsResponse.getRecordList().size(), 3);
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(1L));
        Assert.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(1L));
        Assert.assertNotNull(getAllRecordsResponse.getRecordList().get(0).getDateAdded());
        Assert.assertNotNull(getAllRecordsResponse.getRecordList().get(0).getDateComplete());

        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getTargetId()), Optional.of(2L));
        Assert.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getUserId()), Optional.of(2L));
        Assert.assertNotNull(getAllRecordsResponse.getRecordList().get(1).getDateAdded());
        Assert.assertNull(getAllRecordsResponse.getRecordList().get(1).getDateComplete());

        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(2).getTargetId()), Optional.of(4L));
        Assert.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(2).getUserId()), Optional.of(3L));
        Assert.assertNotNull(getAllRecordsResponse.getRecordList().get(2).getDateAdded());
        Assert.assertNotNull(getAllRecordsResponse.getRecordList().get(2).getDateComplete());
    }
}
