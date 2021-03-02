package java2.application_target_list.core.database;

import java2.application_target_list.core.database.board.BoardRepository;
import java2.application_target_list.core.database.board.InMemoryBoardRepositoryImpl;
import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.domain.Target;
import java2.application_target_list.core.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@SpringBootTest
public class RecordListImplTest {

    private BoardRepository boardRepository;
    private Record record1;
    private Record record2;
    private Record record3;
    private Target firstTarget;
    private Target secondTarget;
    private Target thirdTarget;
    private User firstUser;
    private User secondUser;

    @BeforeEach
    public void setup(){
        boardRepository = new InMemoryBoardRepositoryImpl();
        firstTarget = new Target("name", "description", 5L);
        secondTarget = new Target("name1", "description1", 2L);
        thirdTarget = new Target("name2", "description2", 54L);
        firstUser = new User("name", "surname");
        secondUser = new User("name1", "surname1");
        record1 = new Record(firstTarget.getId(), firstUser.getId());
        record2 = new Record(secondTarget.getId(), firstUser.getId());
        record3 = new Record(thirdTarget.getId(), secondUser.getId());
    }

    @Test
    public void testAddToBoard_v1() {
        boardRepository.addToBoard(record1);
        Assertions.assertEquals(boardRepository.getAllRecordsList().size(), 1);
        Assertions.assertEquals(java.util.Optional.ofNullable(record1.getRecordId()), Optional.of(1L));
        Assertions.assertNotNull(boardRepository.getAllRecordsList().get(0).getDateAdded());
        Assertions.assertNull(boardRepository.getAllRecordsList().get(0).getDateComplete());
    }

    @Test
    public void testAddToBoard_v2() {
        boardRepository.addToBoard(record1);
        boardRepository.addToBoard(record2);
        boardRepository.addToBoard(record3);
        Assertions.assertEquals(boardRepository.getAllRecordsList().size(), 3);
        Assertions.assertEquals(java.util.Optional.ofNullable(record1.getRecordId()), Optional.of(1L));
        Assertions.assertEquals(java.util.Optional.ofNullable(record2.getRecordId()), Optional.of(2L));
        Assertions.assertEquals(java.util.Optional.ofNullable(record3.getRecordId()), Optional.of(3L));
        Assertions.assertNotNull(boardRepository.getAllRecordsList().get(0).getDateAdded());
        Assertions.assertNull(boardRepository.getAllRecordsList().get(0).getDateComplete());
        Assertions.assertNotNull(boardRepository.getAllRecordsList().get(1).getDateAdded());
        Assertions.assertNull(boardRepository.getAllRecordsList().get(1).getDateComplete());
        Assertions.assertNotNull(boardRepository.getAllRecordsList().get(2).getDateAdded());
        Assertions.assertNull(boardRepository.getAllRecordsList().get(2).getDateComplete());
    }

    @Test
    public void testDeleteFromBoard() {
        boardRepository.addToBoard(record1);
        boardRepository.addToBoard(record2);
        boardRepository.addToBoard(record3);
        boardRepository.deleteFromBoard(1L);
        Assertions.assertEquals(boardRepository.getAllRecordsList().size(), 2);
        Assertions.assertEquals(Optional.ofNullable(boardRepository.getAllRecordsList().get(0).getRecordId()), Optional.of(2L));
        Assertions.assertEquals(Optional.ofNullable(boardRepository.getAllRecordsList().get(1).getRecordId()), Optional.of(3L));
    }

    @Test
    public void testGetAllList_v1() {
        boardRepository.addToBoard(record1);
        Assertions.assertEquals(boardRepository.getAllRecordsList().size(), 1);
        Assertions.assertEquals(java.util.Optional.ofNullable(boardRepository.getAllRecordsList().get(0).getRecordId()), Optional.of(1L));
        Assertions.assertEquals(boardRepository.getAllRecordsList().get(0).getTargetId(), firstTarget.getId());
        Assertions.assertEquals(boardRepository.getAllRecordsList().get(0).getUserId(), firstUser.getId());
        Assertions.assertNull(boardRepository.getAllRecordsList().get(0).getDateComplete());
        Assertions.assertNotNull(boardRepository.getAllRecordsList().get(0).getDateAdded());
    }

    @Test
    public void testGetAllList_v2() {
        boardRepository.addToBoard(record1);
        boardRepository.addToBoard(record2);
        boardRepository.addToBoard(record3);
        Assertions.assertEquals(boardRepository.getAllRecordsList().size(), 3);
        Assertions.assertEquals(java.util.Optional.ofNullable(boardRepository.getAllRecordsList().get(0).getRecordId()), Optional.of(1L));
        Assertions.assertEquals(java.util.Optional.ofNullable(boardRepository.getAllRecordsList().get(1).getRecordId()), Optional.of(2L));
        Assertions.assertEquals(java.util.Optional.ofNullable(boardRepository.getAllRecordsList().get(2).getRecordId()), Optional.of(3L));
        Assertions.assertEquals(boardRepository.getAllRecordsList().get(0).getTargetId(), firstTarget.getId());
        Assertions.assertEquals(boardRepository.getAllRecordsList().get(0).getUserId(), firstUser.getId());
        Assertions.assertEquals(boardRepository.getAllRecordsList().get(1).getTargetId(), secondTarget.getId());
        Assertions.assertEquals(boardRepository.getAllRecordsList().get(1).getUserId(), secondUser.getId());
        Assertions.assertEquals(boardRepository.getAllRecordsList().get(2).getTargetId(), thirdTarget.getId());
        Assertions.assertEquals(boardRepository.getAllRecordsList().get(2).getUserId(), firstUser.getId());

    }

    @Test
    public void testSetBoardCompleteDate_v1() {
        boardRepository.addToBoard(record1);
        boardRepository.setRecordCompleteDate(1L);
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String expectedDate =  localDateTime.format(myFormatDate);
        Assertions.assertEquals(boardRepository.getAllRecordsList().get(0).getDateComplete(), expectedDate);
    }

    @Test
    public void testSetBoardCompleteDate_v2() {
        boardRepository.addToBoard(record1);
        boardRepository.addToBoard(record2);
        boardRepository.setRecordCompleteDate(1L);
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter myFormatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String expectedDate =  localDateTime.format(myFormatDate);
        Assertions.assertEquals(boardRepository.getAllRecordsList().get(0).getDateComplete(), expectedDate);
        Assertions.assertNull(boardRepository.getAllRecordsList().get(1).getDateComplete());
    }
}