package java2.application_target_list.core.services.board;

import java2.application_target_list.core.database.BoardDatabase;
import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.requests.board.GetAllRecordsRequest;
import java2.application_target_list.core.responses.board.GetAllRecordsResponse;
import java2.application_target_list.core.services.user.GetAllUserService;
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
import java.util.Optional;

@RunWith(MockitoJUnitRunner.Silent.class)
public class GetAllRecordsServiceTest extends TestCase {

    private List<Record> recordList;
    @Mock BoardDatabase boardDatabase;
    @InjectMocks GetAllRecordsService getAllRecordsService;

    @Before
    public void setup() {
        recordList = new ArrayList<>();
    }

    @Test
    public void shouldReturnDB_v1() {
        recordList.add(new Record(1L, 1L));
        Mockito.when(boardDatabase.getAllRecordsList()).thenReturn(recordList);
        GetAllRecordsRequest getAllRecordsRequest = new GetAllRecordsRequest();
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(getAllRecordsRequest);
        assertFalse(getAllRecordsResponse.hasErrors());
        assertEquals(getAllRecordsResponse.getRecordList().size(), 1);
        assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(1L));
        assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(1L));
    }

    @Test
    public void shouldReturnDB_v2() {
        recordList.add(new Record(1L, 1L));
        recordList.add(new Record(2L, 1L));
        Mockito.when(boardDatabase.getAllRecordsList()).thenReturn(recordList);
        GetAllRecordsRequest getAllRecordsRequest = new GetAllRecordsRequest();
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(getAllRecordsRequest);
        assertFalse(getAllRecordsResponse.hasErrors());
        assertEquals(getAllRecordsResponse.getRecordList().size(), 2);
        assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(1L));
        assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(1L));
        assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getTargetId()), Optional.of(2L));
        assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getUserId()), Optional.of(1L));
    }

    @Test
    public void shouldReturnDB_v3() {
        recordList.add(new Record(1L, 1L));
        recordList.add(new Record(2L, 1L));
        recordList.add(new Record(3L, 2L));
        Mockito.when(boardDatabase.getAllRecordsList()).thenReturn(recordList);
        GetAllRecordsRequest getAllRecordsRequest = new GetAllRecordsRequest();
        GetAllRecordsResponse getAllRecordsResponse = getAllRecordsService.execute(getAllRecordsRequest);
        assertFalse(getAllRecordsResponse.hasErrors());
        assertEquals(getAllRecordsResponse.getRecordList().size(), 3);
        assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getTargetId()), Optional.of(1L));
        assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(0).getUserId()), Optional.of(1L));
        assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getTargetId()), Optional.of(2L));
        assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(1).getUserId()), Optional.of(1L));
        assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(2).getTargetId()), Optional.of(3L));
        assertEquals(java.util.Optional.ofNullable(getAllRecordsResponse.getRecordList().get(2).getUserId()), Optional.of(2L));
    }
}