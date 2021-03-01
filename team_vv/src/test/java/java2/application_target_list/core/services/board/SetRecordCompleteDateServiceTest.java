package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.jpa.JpaBoardRepository;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SetRecordCompleteDateServiceTest extends TestCase {

    private List<CoreError> errors;
    @Mock
    private SetRecordCompleteDateValidator setRecordCompleteDateValidator;
    @Mock
    private JpaBoardRepository jpaBoardRepository;
    @InjectMocks
    SetRecordCompleteDateService setRecordCompleteDateService;

    @Before
    public void setup() {
        errors = new ArrayList<>();
    }

//    @Test
//    public void shouldSetCompleteDate() {
//        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(1L);
//        SetRecordCompleteDateResponse setRecordCompleteDateResponse = setRecordCompleteDateService.execute(setRecordCompleteDateRequest);
//        Mockito.when(jpaBoardRepository.setRecordCompleteDate(1L, getDate())).thenReturn(1);
//        assertFalse(setRecordCompleteDateResponse.getErrorList().isEmpty());
//    }

    @Test
    public void shouldReturnResponseWithErrors_v1() {
        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(1L);
        Mockito.when(setRecordCompleteDateValidator.validate(setRecordCompleteDateRequest)).thenReturn(errors);
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
        Mockito.when(setRecordCompleteDateValidator.validate(setRecordCompleteDateRequest)).thenReturn(errors);
        SetRecordCompleteDateResponse setRecordCompleteDateResponse = setRecordCompleteDateService.execute(setRecordCompleteDateRequest);
        assertTrue(setRecordCompleteDateResponse.hasErrors());
        assertEquals(setRecordCompleteDateResponse.getErrorList().size(), 2);
        assertEquals(setRecordCompleteDateResponse.getErrorList().get(1).getField(), "Record ID");
        assertEquals(setRecordCompleteDateResponse.getErrorList().get(1).getMessage(), "no record with that ID");
        assertEquals(setRecordCompleteDateResponse.getErrorList().get(0).getField(), "Record ID");
        assertEquals(setRecordCompleteDateResponse.getErrorList().get(0).getMessage(), "must not be empty!");
    }

    @Test
    public void shouldReturnResponseWithErrors_v3() {
        errors.add(new CoreError("Record ID","must not be negative!"));
        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(-2L);
        Mockito.when(setRecordCompleteDateValidator.validate(setRecordCompleteDateRequest)).thenReturn(errors);
        SetRecordCompleteDateResponse setRecordCompleteDateResponse = setRecordCompleteDateService.execute(setRecordCompleteDateRequest);
        assertTrue(setRecordCompleteDateResponse.hasErrors());
        assertEquals(setRecordCompleteDateResponse.getErrorList().size(), 2);
        assertEquals(setRecordCompleteDateResponse.getErrorList().get(1).getField(), "Record ID");
        assertEquals(setRecordCompleteDateResponse.getErrorList().get(1).getMessage(), "no record with that ID");
        assertEquals(setRecordCompleteDateResponse.getErrorList().get(0).getField(), "Record ID");
        assertEquals(setRecordCompleteDateResponse.getErrorList().get(0).getMessage(), "must not be negative!");
    }

    private String getDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(myFormatDate);
    }
}