package lv.estore.app.core.validators;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.NameRequest;
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
public class NameValidatorTest {

    @Mock ValidationRules rules;
    @InjectMocks
    NameValidator validator;

    @Test
    public void testValidateName_Successful() {
        Mockito.when(rules.validateName(any())).thenReturn(Optional.empty());

        NameRequest request = new NameRequest("Name");
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testValidateName_Absent() {
        Mockito.when(rules.validateName(any()))
                .thenReturn(Optional.of(new CoreError("Name",  "Field should not be empty")));

        NameRequest request = new NameRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.size() == 1);
        assertTrue(errors.stream().anyMatch(s-> "Name".equals(s.getField())));
        assertTrue(errors.stream().anyMatch(s-> "Field should not be empty".equals(s.getMessage())));
    }

    @Test
    public void testValidateName_Empty() {
        Mockito.when(rules.validateName(any()))
                .thenReturn(Optional.of(new CoreError("Name",  "Field should not be empty")));

        NameRequest request = new NameRequest("");
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.size() == 1);
        assertTrue(errors.stream().anyMatch(s-> "Name".equals(s.getField())));
        assertTrue(errors.stream().anyMatch(s-> "Field should not be empty".equals(s.getMessage())));
    }

    @Test
    public void testValidateName_Invalid() {
        Mockito.when(rules.validateName(any()))
                .thenReturn(Optional.of(new CoreError("Name",  "Field should be valid")));

        NameRequest request = new NameRequest("123dos");
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.size() == 1);
        assertTrue(errors.stream().anyMatch(s-> "Name".equals(s.getField())));
        assertTrue(errors.stream().anyMatch(s-> "Field should be valid".equals(s.getMessage())));
    }
}