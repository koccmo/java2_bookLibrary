package java2.application_target_list.core.validators.board;

import java2.application_target_list.core.database.BoardDatabase;
import java2.application_target_list.core.database.BoardListImpl;
import java2.application_target_list.core.domain.Record;
import java2.application_target_list.core.requests.board.SetRecordCompleteDateRequest;
import java2.application_target_list.core.responses.CoreError;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SetRecordCompleteDateValidatorTest {

    private SetRecordCompleteDateValidator setRecordCompleteDateValidator;
    private BoardDatabase boardDatabase;

    @Before
    public void setup(){
        setRecordCompleteDateValidator = new SetRecordCompleteDateValidator();
        boardDatabase = new BoardListImpl();
        boardDatabase.addToBoard(new Record(1L, 1L));
    }

    @Test
    public void testValidate_validRequest() {
        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(1L);
        List<CoreError> actualErrors = setRecordCompleteDateValidator.validate(setRecordCompleteDateRequest, boardDatabase);
        Assert.assertTrue(actualErrors.isEmpty());
    }

    @Test
    public void testValidate_invalidRequest_v1() {
        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(2L);
        List<CoreError> actualErrors = setRecordCompleteDateValidator.validate(setRecordCompleteDateRequest, boardDatabase);
        Assert.assertFalse(actualErrors.isEmpty());
        Assert.assertEquals(actualErrors.size(), 1);
        Assert.assertEquals(actualErrors.get(0).getField(), "Record ID");
        Assert.assertEquals(actualErrors.get(0).getMessage(), "no record with that ID");
    }

    @Test
    public void testValidate_invalidRequest_v2() {
        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(-2L);
        List<CoreError> actualErrors = setRecordCompleteDateValidator.validate(setRecordCompleteDateRequest, boardDatabase);
        Assert.assertFalse(actualErrors.isEmpty());
        Assert.assertEquals(actualErrors.size(), 2);
        Assert.assertEquals(actualErrors.get(0).getField(), "Record ID");
        Assert.assertEquals(actualErrors.get(0).getMessage(), "no record with that ID");
        Assert.assertEquals(actualErrors.get(1).getField(), "Record ID");
        Assert.assertEquals(actualErrors.get(1).getMessage(), "must not be negative!");
    }

//    @Test
//    public void testValidate_invalidRequest_v3() {
//        SetRecordCompleteDateRequest setRecordCompleteDateRequest = new SetRecordCompleteDateRequest(null);
//        List<CoreError> actualErrors = setRecordCompleteDateValidator.validate(setRecordCompleteDateRequest, boardDatabase);
//        Assert.assertFalse(actualErrors.isEmpty());
//        Assert.assertEquals(actualErrors.size(), 2);
//        Assert.assertEquals(actualErrors.get(0).getField(), "Record ID");
//        Assert.assertEquals(actualErrors.get(0).getMessage(), "no record with that ID");
//        Assert.assertEquals(actualErrors.get(1).getField(), "Record ID");
//        Assert.assertEquals(actualErrors.get(1).getMessage(), "must not be negative!");
//    }
}