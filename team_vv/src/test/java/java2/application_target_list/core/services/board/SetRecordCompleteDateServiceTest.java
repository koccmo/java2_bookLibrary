package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.BoardDatabase;
import java2.application_target_list.core.requests.board.SetRecordCompleteDateRequest;
import java2.application_target_list.core.responses.CoreError;
import java2.application_target_list.core.responses.board.SetRecordCompleteDateResponse;
import java2.application_target_list.core.validators.board.SetRecordCompleteDateValidator;
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
public class SetRecordCompleteDateServiceTest extends TestCase {

    private List<CoreError> errors;
    @Mock SetRecordCompleteDateValidator setRecordCompleteDateValidator;
    @Mock BoardDatabase boardDatabase;
    @InjectMocks SetRecordCompleteDateService setRecordCompleteDateService;

    @Before
    public void setup() {
        errors = new ArrayList<>();
    }

    @Test
    public void shouldSetCompleteDate() {
        Mockito.when(boardDatabase.setRecordCompleteDate(1L)).thenReturn(true);
        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(1L);
        SetRecordCompleteDateResponse setRecordCompleteDateResponse = setRecordCompleteDateService.execute(setRecordCompleteDateRequest);
        assertFalse(setRecordCompleteDateResponse.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithErrors_v1() {
        errors.add(new CoreError("Record ID","no record with that ID"));
        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(1L);
        Mockito.when(setRecordCompleteDateValidator.validate(setRecordCompleteDateRequest, boardDatabase)).thenReturn(errors);
        SetRecordCompleteDateResponse setRecordCompleteDateResponse = setRecordCompleteDateService.execute(setRecordCompleteDateRequest);
        assertTrue(setRecordCompleteDateResponse.hasErrors());
        assertEquals(setRecordCompleteDateResponse.getErrorList().size(), 1);
        assertEquals(setRecordCompleteDateResponse.getErrorList().get(0).getField(), "Record ID");
        assertEquals(setRecordCompleteDateResponse.getErrorList().get(0).getMessage(), "no record with that ID");
    }

    @Test
    public void shouldReturnResponseWithErrors_v2() {
        errors.add(new CoreError("Record ID","must not be empty!"));
        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(null);
        Mockito.when(setRecordCompleteDateValidator.validate(setRecordCompleteDateRequest, boardDatabase)).thenReturn(errors);
        SetRecordCompleteDateResponse setRecordCompleteDateResponse = setRecordCompleteDateService.execute(setRecordCompleteDateRequest);
        assertTrue(setRecordCompleteDateResponse.hasErrors());
        assertEquals(setRecordCompleteDateResponse.getErrorList().size(), 1);
        assertEquals(setRecordCompleteDateResponse.getErrorList().get(0).getField(), "Record ID");
        assertEquals(setRecordCompleteDateResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v3() {
        errors.add(new CoreError("Record ID","must not be negative!"));
        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(-2L);
        Mockito.when(setRecordCompleteDateValidator.validate(setRecordCompleteDateRequest, boardDatabase)).thenReturn(errors);
        SetRecordCompleteDateResponse setRecordCompleteDateResponse = setRecordCompleteDateService.execute(setRecordCompleteDateRequest);
        assertTrue(setRecordCompleteDateResponse.hasErrors());
        assertEquals(setRecordCompleteDateResponse.getErrorList().size(), 1);
        assertEquals(setRecordCompleteDateResponse.getErrorList().get(0).getField(), "Record ID");
        assertEquals(setRecordCompleteDateResponse.getErrorList().get(0).getMessage(), "must not be negative!");
    }
}