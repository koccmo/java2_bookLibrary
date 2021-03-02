package java2.application_target_list.core.validators.board;

import java2.application_target_list.core.database.board.BoardRepository;
import java2.application_target_list.core.database.board.InMemoryBoardRepositoryImpl;
import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.requests.board.SetRecordCompleteDateRequest;
import java2.application_target_list.core.responses.CoreError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class SetRecordCompleteDateValidatorTest {

    @Autowired
    private SetRecordCompleteDateValidator setRecordCompleteDateValidator;

    @BeforeEach
    public void setup(){
        BoardRepository boardRepository = new InMemoryBoardRepositoryImpl();
        boardRepository.addToBoard(new Record(1L, 1L));
    }

    @Test
    public void testValidate_validRequest() {
        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(1L);
        List<CoreError> actualErrors = setRecordCompleteDateValidator.validate(setRecordCompleteDateRequest);
        Assertions.assertTrue(actualErrors.isEmpty());
    }

    @Test
    public void testValidate_invalidRequest_v2() {
        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(-2L);
        List<CoreError> actualErrors = setRecordCompleteDateValidator.validate(setRecordCompleteDateRequest);
        Assertions.assertFalse(actualErrors.isEmpty());
        Assertions.assertEquals(actualErrors.size(), 1);
        Assertions.assertEquals(actualErrors.get(0).getField(), "Record ID");
        Assertions.assertEquals(actualErrors.get(0).getMessage(), "must not be negative!");
    }

    @Test
    public void testValidate_invalidRequest_v3() {
        try {
            SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(null);
            List<CoreError> actualErrors = setRecordCompleteDateValidator.validate(setRecordCompleteDateRequest);
            Assertions.assertFalse(actualErrors.isEmpty());
            Assertions.assertEquals(actualErrors.size(), 1);
            Assertions.assertEquals(actualErrors.get(0).getField(), "Record ID");
            Assertions.assertEquals(actualErrors.get(0).getMessage(), "must not be negative!");
        } catch (NullPointerException ignored) {

        }
    }
}