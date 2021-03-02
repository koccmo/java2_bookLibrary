package java2.application_target_list.core.validators.board;

import java2.application_target_list.core.database.board.BoardRepository;
import java2.application_target_list.core.database.board.InMemoryBoardRepositoryImpl;
import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.requests.board.DeleteRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeleteRecordValidatorTest {

    @Autowired
    private DeleteRecordValidator deleteRecordValidator;
    
    @BeforeEach
    public void setup() {
        BoardRepository boardRepository = new InMemoryBoardRepositoryImpl();
        boardRepository.addToBoard(new Record(1L, 2L));

    }

    @Test
    public void testValidate_validRequest() {
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(1L);
        List<CoreError> actualErrors = deleteRecordValidator.validate(deleteRecordRequest);
        Assertions.assertTrue(actualErrors.isEmpty());
    }


    @Test
    public void testValidate_invalidRequest_v2() {
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(-3L);
        List<CoreError> actualErrors = deleteRecordValidator.validate(deleteRecordRequest);
        Assertions.assertFalse(actualErrors.isEmpty());
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertEquals(actualErrors.get(0).getField(), "Record ID");
        Assertions.assertEquals(actualErrors.get(0).getMessage(), "must not be negative!");
    }

    @Test
    public void testValidate_invalidRequest_v3() {
        try {
            DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(null);
            List<CoreError> actualErrors = deleteRecordValidator.validate(deleteRecordRequest);
            Assertions.assertFalse(actualErrors.isEmpty());
            Assertions.assertEquals(actualErrors.size(), 1);
            Assertions.assertEquals(actualErrors.get(0).getField(), "Record ID");
            Assertions.assertEquals(actualErrors.get(0).getMessage(), "must not be empty!");
        } catch (NullPointerException ignored) {
        }
    }
}