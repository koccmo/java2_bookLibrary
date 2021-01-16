package java2.application_target_list.core.acceptancetests.board;

import java2.application_target_list.config.TargetListConfiguration;
import java2.application_target_list.core.database.TargetDatabase;
import java2.application_target_list.core.database.TargetListImpl;
import java2.application_target_list.core.database.UserDatabase;
import java2.application_target_list.core.database.UserListImpl;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.domain.User;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.requests.board.DeleteRecordRequest;
import java2.application_target_list.core.requests.board.GetAllRecordsRequest;
import java2.application_target_list.core.responses.board.DeleteRecordResponse;
import java2.application_target_list.core.responses.board.GetAllRecordsResponse;
import java2.application_target_list.core.services.board.AddRecordService;
import java2.application_target_list.core.services.board.DeleteRecordService;
import java2.application_target_list.core.services.board.GetAllRecordsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class DeleteRecordAcceptanceTest {

    private DeleteRecordService deleteRecordService;
    private GetAllRecordsService getAllRecordsService;

    @Before
    public void setup(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TargetListConfiguration.class);
        AddRecordService addRecordService = applicationContext.getBean(AddRecordService.class);
        deleteRecordService = applicationContext.getBean(DeleteRecordService.class);
        getAllRecordsService = applicationContext.getBean(GetAllRecordsService.class);
        UserDatabase userDatabase = applicationContext.getBean(UserListImpl.class);
        TargetDatabase targetDatabase = applicationContext.getBean(TargetListImpl.class);
        userDatabase.addUser(new User("name1", "surname1"));
        targetDatabase.addTarget(new Target("name", "description", 1));
        targetDatabase.addTarget(new Target("name2", "description2", 2));
        AddRecordRequest addRecordRequest1 = new AddRecordRequest(1L, 1L);
        AddRecordRequest addRecordRequest2 = new AddRecordRequest(2L, 1L);
        addRecordService.execute(addRecordRequest1);
        addRecordService.execute(addRecordRequest2);
    }

    @Test
    public void shouldDeleteRecordFromList_v1() {
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(1L);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());
        Assert.assertFalse(deleteRecordResponse.hasErrors());
        Assert.assertEquals(getAllRecordsResponse.getRecordList().size(), 1);
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(2L));
        Assert.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(1L));
    }

    @Test
    public void shouldDeleteRecordFromList_v2() {
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(2L);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(new GetAllRecordsRequest());
        Assert.assertFalse(deleteRecordResponse.hasErrors());
        Assert.assertEquals(getAllRecordsResponse.getRecordList().size(), 1);
        Assert.assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(1L));
        Assert.assertEquals(Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(1L));
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
}
