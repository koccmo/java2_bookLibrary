package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.board.BoardDatabase;
import java2.application_target_list.core.database.target.TargetDatabase;
import java2.application_target_list.core.database.user.UserDatabase;
import java2.application_target_list.core.matchers.RecordMatcher;
import java2.application_target_list.core.requests.board.AddRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.board.AddRecordResponse;
import java2.application_target_list.core.validators.board.AddRecordValidator;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AddRecordServiceTest extends TestCase {

    private List<CoreError> errorList;
    @Mock AddRecordValidator addRecordValidator;
    @Mock BoardDatabase boardDatabase;
    @Mock UserDatabase userDatabase;
    @Mock TargetDatabase targetDatabase;
    @InjectMocks AddRecordService addRecordService;

    @Before
    public void setup() {
        errorList = new ArrayList<>();
    }

    @Test
    public void shouldAddRecordToDatabase() {
        Mockito.when(addRecordValidator.validate(any())).thenReturn(new ArrayList<>());
        AddRecordRequest recordRequest = new AddRecordRequest(1L, 1L);
        AddRecordResponse addRecordResponse = addRecordService.execute(recordRequest);
        assertFalse(addRecordResponse.hasErrors());
        Mockito.verify(boardDatabase).addToBoard(argThat(new RecordMatcher(1L, 1L)));
    }

    @Test
    public void shouldReturnResponseWithErrors_v1() {
        errorList.add(new CoreError("Target ID", "must not be empty!"));
        Mockito.when(addRecordValidator.validate(any())).thenReturn(errorList);
        AddRecordRequest recordRequest = new AddRecordRequest(null, 1L);
        AddRecordResponse addRecordResponse = addRecordService.execute(recordRequest);
        assertTrue(addRecordResponse.hasErrors());
        assertEquals(addRecordResponse.getErrorList().size(), 1);
        assertEquals(addRecordResponse.getErrorList().get(0).getField(),"Target ID");
        assertEquals(addRecordResponse.getErrorList().get(0).getMessage(),"must not be empty!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v2() {
        errorList.add(new CoreError("Target ID", "must not be negative!"));
        Mockito.when(addRecordValidator.validate(any())).thenReturn(errorList);
        AddRecordRequest recordRequest = new AddRecordRequest(-2L, 1L);
        AddRecordResponse addRecordResponse = addRecordService.execute(recordRequest);
        assertTrue(addRecordResponse.hasErrors());
        assertEquals(addRecordResponse.getErrorList().size(), 1);
        assertEquals(addRecordResponse.getErrorList().get(0).getField(),"Target ID");
        assertEquals(addRecordResponse.getErrorList().get(0).getMessage(),"must not be negative!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v3() {
        errorList.add(new CoreError("User ID", "must not be negative!"));
        Mockito.when(addRecordValidator.validate(any())).thenReturn(errorList);
        AddRecordRequest recordRequest = new AddRecordRequest(1L, -1L);
        AddRecordResponse addRecordResponse = addRecordService.execute(recordRequest);
        assertTrue(addRecordResponse.hasErrors());
        assertEquals(addRecordResponse.getErrorList().size(), 1);
        assertEquals(addRecordResponse.getErrorList().get(0).getField(),"User ID");
        assertEquals(addRecordResponse.getErrorList().get(0).getMessage(),"must not be negative!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v4() {
        errorList.add(new CoreError("User ID", "must not be empty!"));
        Mockito.when(addRecordValidator.validate(any())).thenReturn(errorList);
        AddRecordRequest recordRequest = new AddRecordRequest(1L, null);
        AddRecordResponse addRecordResponse = addRecordService.execute(recordRequest);
        assertTrue(addRecordResponse.hasErrors());
        assertEquals(addRecordResponse.getErrorList().size(), 1);
        assertEquals(addRecordResponse.getErrorList().get(0).getField(),"User ID");
        assertEquals(addRecordResponse.getErrorList().get(0).getMessage(),"must not be empty!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v5() {
        errorList.add(new CoreError("Target ID", "must not be negative!"));
        errorList.add(new CoreError("User ID", "must not be empty!"));
        Mockito.when(addRecordValidator.validate(any())).thenReturn(errorList);
        AddRecordRequest recordRequest = new AddRecordRequest(-1L, null);
        AddRecordResponse addRecordResponse = addRecordService.execute(recordRequest);
        assertTrue(addRecordResponse.hasErrors());
        assertEquals(addRecordResponse.getErrorList().size(), 2);
        assertEquals(addRecordResponse.getErrorList().get(0).getField(),"Target ID");
        assertEquals(addRecordResponse.getErrorList().get(0).getMessage(),"must not be negative!");
        assertEquals(addRecordResponse.getErrorList().get(1).getField(),"User ID");
        assertEquals(addRecordResponse.getErrorList().get(1).getMessage(),"must not be empty!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v6() {
        errorList.add(new CoreError("Target ID", "no target with that ID!"));
        errorList.add(new CoreError("User ID", "no user with that ID!"));
        Mockito.when(addRecordValidator.validate(any())).thenReturn(errorList);
        AddRecordRequest recordRequest = new AddRecordRequest(2L, 2L);
        AddRecordResponse addRecordResponse = addRecordService.execute(recordRequest);
        assertTrue(addRecordResponse.hasErrors());
        assertEquals(addRecordResponse.getErrorList().size(), 2);
        assertEquals(addRecordResponse.getErrorList().get(0).getField(),"Target ID");
        assertEquals(addRecordResponse.getErrorList().get(0).getMessage(),"no target with that ID!");
        assertEquals(addRecordResponse.getErrorList().get(1).getField(),"User ID");
        assertEquals(addRecordResponse.getErrorList().get(1).getMessage(),"no user with that ID!");
    }
}