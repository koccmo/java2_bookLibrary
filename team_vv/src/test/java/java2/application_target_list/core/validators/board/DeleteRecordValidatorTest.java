package java2.application_target_list.core.validators.board;

import java2.application_target_list.core.database.board.BoardRepository;
import java2.application_target_list.core.database.board.InMemoryBoardRepositoryImpl;
import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.requests.board.DeleteRecordRequest;
import java2.application_target_list.core.responses.CoreError;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;


public class DeleteRecordValidatorTest {

    private DeleteRecordValidator deleteRecordValidator;
    private BoardRepository boardRepository;

    @Before
    public void setup() {
        deleteRecordValidator = new DeleteRecordValidator();
        boardRepository = new InMemoryBoardRepositoryImpl();
        boardRepository.addToBoard(new Record(1L, 2L));
    }

    @Test
    public void testValidate_validRequest() {
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(1L);
        List<CoreError> actualErrors = deleteRecordValidator.validate(deleteRecordRequest, boardRepository);
        Assert.assertTrue(actualErrors.isEmpty());
    }

    @Test
    public void testValidate_invalidRequest_v1() {
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(3L);
        List<CoreError> actualErrors = deleteRecordValidator.validate(deleteRecordRequest, boardRepository);
        Assert.assertFalse(actualErrors.isEmpty());
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertEquals(actualErrors.get(0).getField(), "Record ID");
        Assert.assertEquals(actualErrors.get(0).getMessage(), "no record with that ID");
    }

    @Test
    public void testValidate_invalidRequest_v2() {
        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(-3L);
        List<CoreError> actualErrors = deleteRecordValidator.validate(deleteRecordRequest, boardRepository);
        Assert.assertFalse(actualErrors.isEmpty());
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertEquals(actualErrors.get(0).getField(), "Record ID");
        Assert.assertEquals(actualErrors.get(0).getMessage(), "no record with that ID");
        Assert.assertEquals(actualErrors.get(1).getField(), "Record ID");
        Assert.assertEquals(actualErrors.get(1).getMessage(), "must not be negative!");
    }

//    @Test
//    public void testValidate_invalidRequest_v3() {
//        DeleteRecordRequest deleteRecordRequest = new DeleteRecordRequest(null);
//        List<CoreError> actualErrors = deleteRecordValidator.validate(deleteRecordRequest, boardDatabase);
//        Assert.assertFalse(actualErrors.isEmpty());
//        Assert.assertEquals(actualErrors.size(), 2);
//        Assert.assertEquals(actualErrors.get(0).getField(), "Record ID");
//        Assert.assertEquals(actualErrors.get(0).getMessage(), "no record with that ID");
//        Assert.assertEquals(actualErrors.get(1).getField(), "Record ID");
//        Assert.assertEquals(actualErrors.get(1).getMessage(), "must not be empty!");
//    }
}