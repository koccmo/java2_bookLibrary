package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.BoardDatabase;
import java2.application_target_list.core.requests.board.DeleteRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.board.DeleteRecordResponse;
import java2.application_target_list.core.validators.board.DeleteRecordValidator;
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

@RunWith(MockitoJUnitRunner.Silent.class)
public class DeleteRecordServiceTest extends TestCase {

    private List<CoreError> errors;
    @Mock DeleteRecordValidator deleteRecordValidator;
    @Mock BoardDatabase boardDatabase;
    @InjectMocks DeleteRecordService deleteRecordService;

    @Before
    public void setup() {
        errors = new ArrayList<>();
    }

    @Test
    public void shouldDeleteRecordFromDatabase() {
        Mockito.when(boardDatabase.isIdInBoardList(1L)).thenReturn(true);
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(1L);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        assertFalse(deleteRecordResponse.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithErrors_v1() {
        errors.add(new CoreError("Record ID","no record with that ID"));
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(1L);
        Mockito.when(deleteRecordValidator.validate(deleteRecordRequest, boardDatabase)).thenReturn(errors);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        assertTrue(deleteRecordResponse.hasErrors());
        assertEquals(deleteRecordResponse.getErrorList().size(), 1);
        assertEquals(deleteRecordResponse.getErrorList().get(0).getField(), "Record ID");
        assertEquals(deleteRecordResponse.getErrorList().get(0).getMessage(), "no record with that ID");
    }

    @Test
    public void shouldReturnResponseWithErrors_v2() {
        errors.add(new CoreError("Record ID","must not be empty!"));
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(null);
        Mockito.when(deleteRecordValidator.validate(deleteRecordRequest, boardDatabase)).thenReturn(errors);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        assertTrue(deleteRecordResponse.hasErrors());
        assertEquals(deleteRecordResponse.getErrorList().size(), 1);
        assertEquals(deleteRecordResponse.getErrorList().get(0).getField(), "Record ID");
        assertEquals(deleteRecordResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v3() {
        errors.add(new CoreError("Record ID","must not be negative!"));
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(-2L);
        Mockito.when(deleteRecordValidator.validate(deleteRecordRequest, boardDatabase)).thenReturn(errors);
        DeleteRecordResponse deleteRecordResponse = deleteRecordService.execute(deleteRecordRequest);
        assertTrue(deleteRecordResponse.hasErrors());
        assertEquals(deleteRecordResponse.getErrorList().size(), 1);
        assertEquals(deleteRecordResponse.getErrorList().get(0).getField(), "Record ID");
        assertEquals(deleteRecordResponse.getErrorList().get(0).getMessage(), "must not be negative!");
    }
}