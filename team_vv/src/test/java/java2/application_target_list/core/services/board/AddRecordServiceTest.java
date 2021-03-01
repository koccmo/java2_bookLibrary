package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.jpa.JpaBoardRepository;
import java2.application_target_list.core.database.jpa.JpaTargetRepository;
import java2.application_target_list.core.database.jpa.JpaUserRepository;
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
import org.mockito.internal.matchers.Null;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AddRecordServiceTest extends TestCase {

    private List<CoreError> errorList;
    @Mock
    private AddRecordValidator addRecordValidator;
    @Mock
    private JpaUserRepository jpaUserRepository;
    @Mock
    private JpaTargetRepository jpaTargetRepository;
    @Mock
    private JpaBoardRepository jpaBoardRepository;
    @InjectMocks
    private AddRecordService addRecordService;

    @Before
    public void setup() {
        errorList = new ArrayList<>();
    }

//    @Test
//    public void shouldAddRecordToDatabase() {
//            Mockito.when(addRecordValidator.validate(any())).thenReturn(new ArrayList<>());
//            Mockito.when(jpaBoardRepository.save(any())).thenReturn(true);
//            AddRecordRequest recordRequest = new AddRecordRequest(1L, 1L);
//            AddRecordResponse addRecordResponse = addRecordService.execute(recordRequest);
//            assertFalse(addRecordResponse.hasErrors());
////        Mockito.verify(boardRepository).addToBoard(argThat(new RecordMatcher(1L, 1L)));
//            Mockito.verify(jpaBoardRepository).save(argThat(new RecordMatcher(1L, 1L)));
//    }

    @Test
    public void shouldReturnResponseWithErrors_v1() {
            errorList.add(new CoreError("Target ID", "must not be empty!"));
            Mockito.when(addRecordValidator.validate(any())).thenReturn(errorList);
            AddRecordRequest recordRequest = new AddRecordRequest(null, 1L);
            AddRecordResponse addRecordResponse = addRecordService.execute(recordRequest);
            assertTrue(addRecordResponse.hasErrors());
            assertEquals(addRecordResponse.getErrorList().size(), 3);
            assertEquals(addRecordResponse.getErrorList().get(0).getField(), "Target ID");
            assertEquals(addRecordResponse.getErrorList().get(0).getMessage(), "must not be empty!");
            assertEquals(addRecordResponse.getErrorList().get(1).getField(), "Target ID");
            assertEquals(addRecordResponse.getErrorList().get(1).getMessage(), "no target with that ID!");
            assertEquals(addRecordResponse.getErrorList().get(2).getField(), "User ID");
            assertEquals(addRecordResponse.getErrorList().get(2).getMessage(), "no user with that ID!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v2() {
        errorList.add(new CoreError("Target ID", "must not be negative!"));
        Mockito.when(addRecordValidator.validate(any())).thenReturn(errorList);
        AddRecordRequest recordRequest = new AddRecordRequest(-2L, 1L);
        AddRecordResponse addRecordResponse = addRecordService.execute(recordRequest);
        assertTrue(addRecordResponse.hasErrors());
        assertEquals(addRecordResponse.getErrorList().size(), 3);
        assertEquals(addRecordResponse.getErrorList().get(0).getField(),"Target ID");
        assertEquals(addRecordResponse.getErrorList().get(0).getMessage(),"must not be negative!");
        assertEquals(addRecordResponse.getErrorList().get(1).getField(), "Target ID");
        assertEquals(addRecordResponse.getErrorList().get(1).getMessage(), "no target with that ID!");
        assertEquals(addRecordResponse.getErrorList().get(2).getField(), "User ID");
        assertEquals(addRecordResponse.getErrorList().get(2).getMessage(), "no user with that ID!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v3() {
        errorList.add(new CoreError("User ID", "must not be negative!"));
        Mockito.when(addRecordValidator.validate(any())).thenReturn(errorList);
        AddRecordRequest recordRequest = new AddRecordRequest(1L, -1L);
        AddRecordResponse addRecordResponse = addRecordService.execute(recordRequest);
        assertTrue(addRecordResponse.hasErrors());
        assertEquals(addRecordResponse.getErrorList().size(), 3);
        assertEquals(addRecordResponse.getErrorList().get(0).getField(),"User ID");
        assertEquals(addRecordResponse.getErrorList().get(0).getMessage(),"must not be negative!");
        assertEquals(addRecordResponse.getErrorList().get(1).getField(), "Target ID");
        assertEquals(addRecordResponse.getErrorList().get(1).getMessage(), "no target with that ID!");
        assertEquals(addRecordResponse.getErrorList().get(2).getField(), "User ID");
        assertEquals(addRecordResponse.getErrorList().get(2).getMessage(), "no user with that ID!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v4() {
        errorList.add(new CoreError("User ID", "must not be empty!"));
        Mockito.when(addRecordValidator.validate(any())).thenReturn(errorList);
        AddRecordRequest recordRequest = new AddRecordRequest(1L, null);
        AddRecordResponse addRecordResponse = addRecordService.execute(recordRequest);
        assertTrue(addRecordResponse.hasErrors());
        assertEquals(addRecordResponse.getErrorList().size(), 3);
        assertEquals(addRecordResponse.getErrorList().get(0).getField(),"User ID");
        assertEquals(addRecordResponse.getErrorList().get(0).getMessage(),"must not be empty!");
        assertEquals(addRecordResponse.getErrorList().get(1).getField(), "Target ID");
        assertEquals(addRecordResponse.getErrorList().get(1).getMessage(), "no target with that ID!");
        assertEquals(addRecordResponse.getErrorList().get(2).getField(), "User ID");
        assertEquals(addRecordResponse.getErrorList().get(2).getMessage(), "no user with that ID!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v5() {
        errorList.add(new CoreError("Target ID", "must not be negative!"));
        errorList.add(new CoreError("User ID", "must not be empty!"));
        Mockito.when(addRecordValidator.validate(any())).thenReturn(errorList);
        AddRecordRequest recordRequest = new AddRecordRequest(-1L, null);
        AddRecordResponse addRecordResponse = addRecordService.execute(recordRequest);
        assertTrue(addRecordResponse.hasErrors());
        assertEquals(addRecordResponse.getErrorList().size(), 4);
        assertEquals(addRecordResponse.getErrorList().get(0).getField(),"Target ID");
        assertEquals(addRecordResponse.getErrorList().get(0).getMessage(),"must not be negative!");
        assertEquals(addRecordResponse.getErrorList().get(1).getField(),"User ID");
        assertEquals(addRecordResponse.getErrorList().get(1).getMessage(),"must not be empty!");
        assertEquals(addRecordResponse.getErrorList().get(2).getField(), "Target ID");
        assertEquals(addRecordResponse.getErrorList().get(2).getMessage(), "no target with that ID!");
        assertEquals(addRecordResponse.getErrorList().get(3).getField(), "User ID");
        assertEquals(addRecordResponse.getErrorList().get(3).getMessage(), "no user with that ID!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v6() {

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