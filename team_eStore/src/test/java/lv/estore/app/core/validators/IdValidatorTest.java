package lv.estore.app.core.validators;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.IdRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class IdValidatorTest {

    @Mock ValidationRules rules;
    @InjectMocks
    IdValidator idValidator;

    @Test
    public void testValidateId_Successful() {
        Mockito.when(rules.validateId(any())).thenReturn(Optional.empty());

        IdRequest request = new IdRequest("1");
        List<CoreError> errors = idValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testValidateId_IdAbsent() {
        Mockito.when(rules.validateId(any())).thenReturn(Optional.of(new CoreError("Id",  "Field should not be empty")));

        IdRequest request = new IdRequest(null);
        List<CoreError> errors = idValidator.validate(request);
        assertTrue(errors.size() == 1);
        assertTrue(errors.stream().anyMatch(s-> "Id".equals(s.getField())));
        assertTrue(errors.stream().anyMatch(s-> "Field should not be empty".equals(s.getMessage())));
    }

    @Test
    public void testValidateId_IdEmpty() {
        Mockito.when(rules.validateId(any())).thenReturn(Optional.of(new CoreError("Id",  "Field should not be empty")));

        IdRequest request = new IdRequest("");
        List<CoreError> errors = idValidator.validate(request);
        assertTrue(errors.size() == 1);
        assertTrue(errors.stream().anyMatch(s-> "Id".equals(s.getField())));
        assertTrue(errors.stream().anyMatch(s-> "Field should not be empty".equals(s.getMessage())));
    }

    @Test
    public void testValidateId_Invalid() {
        Mockito.when(rules.validateId(any())).thenReturn(Optional.of(new CoreError("Id",  "Value should be valid")));

        IdRequest request = new IdRequest("-1");
        List<CoreError> errors = idValidator.validate(request);
        assertTrue(errors.size() == 1);
        assertTrue(errors.stream().anyMatch(s-> "Id".equals(s.getField())));
        assertTrue(errors.stream().anyMatch(s-> "Value should be valid".equals(s.getMessage())));
    }
}