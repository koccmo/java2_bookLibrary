package lv.estore.app.core.validators;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.UpdateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class UpdateValidatorTest {

    @Mock ValidationRules rules;

    @InjectMocks UpdateValidator updateValidator;

    @Test
    public void testValidate_AllValid() {
        Mockito.when(rules.validateId("1")).thenReturn(Optional.empty());
        Mockito.when(rules.validateName("pen")).thenReturn(Optional.empty());
        Mockito.when(rules.validateDescription("red")).thenReturn(Optional.empty());
        Mockito.when(rules.validatePrice("1.0")).thenReturn(Optional.empty());

        UpdateRequest request = new UpdateRequest("1", "pen", "red", "1.0");
        List<CoreError> errors = updateValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testValidate_AllAbsent() {
        Mockito.when(rules.validateId(null)).thenReturn(Optional.of(new CoreError("Id", "Field should not be empty")));
        Mockito.when(rules.validateName(null)).thenReturn(Optional.of(new CoreError("Name", "Field should not be empty")));
        Mockito.when(rules.validateDescription(null)).thenReturn(Optional.of(new CoreError("Description", "Field should not be empty")));
        Mockito.when(rules.validatePrice(null)).thenReturn(Optional.of(new CoreError("Price", "Field should not be empty")));

        UpdateRequest request = new UpdateRequest(null, null, null, null);
        List<CoreError> errors = updateValidator.validate(request);
        assertTrue(errors.size() == 4);
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("Id") && s.getMessage().equals("Field should not be empty")));
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("Name") && s.getMessage().equals("Field should not be empty")));
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("Description") && s.getMessage().equals("Field should not be empty")));
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("Price") && s.getMessage().equals("Field should not be empty")));
    }

    @Test
    public void testValidate_Invalid() {
        Mockito.when(rules.validateId("-1")).thenReturn(Optional.of(new CoreError("Id", "Value should be valid")));
        Mockito.when(rules.validatePrice("XYZ")).thenReturn(Optional.of(new CoreError("Price", "Value should be valid")));

        UpdateRequest request = new UpdateRequest("-1", "pen", "red", "XYZ");
        List<CoreError> errors = updateValidator.validate(request);
        assertTrue("Errors size = " + errors.size(), errors.size() == 2);
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("Id") && s.getMessage().equals("Value should be valid")));
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("Price") && s.getMessage().equals("Value should be valid")));
    }
}