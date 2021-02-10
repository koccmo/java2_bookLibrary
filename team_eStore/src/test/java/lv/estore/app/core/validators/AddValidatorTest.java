package lv.estore.app.core.validators;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.AddRequest;
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
public class AddValidatorTest {

    @Mock ValidationRules rules;

    @InjectMocks AddValidator addValidator;

    @Test
    public void testValidateAdd_Success_AllFieldsAreUsed() {
        Mockito.when(rules.validateName(any())).thenReturn(Optional.empty());
        Mockito.when(rules.validateDescription(any())).thenReturn(Optional.empty());
        Mockito.when(rules.validatePrice(any())).thenReturn(Optional.empty());
        AddRequest request = new AddRequest("pen", "description", "1.10");
        List<CoreError> errors = addValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testValidateAdd_Fail_AllFieldAbsent() {
        Mockito.when(rules.validateName(null))
                .thenReturn(Optional.of(new CoreError("Name",  "Field should not be empty")));
        Mockito.when(rules.validateDescription(null))
                .thenReturn(Optional.of(new CoreError("Description",  "Field should not be empty")));
        Mockito.when(rules.validatePrice(null))
                .thenReturn(Optional.of(new CoreError("Price",  "Field should not be empty")));

        AddRequest request = new AddRequest(null, null, null);
        List<CoreError> errors = addValidator.validate(request);
        assertTrue(errors.size() == 3);
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("Name") && s.getMessage().equals("Field should not be empty")));
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("Description") && s.getMessage().equals("Field should not be empty")));
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("Price") && s.getMessage().equals("Field should not be empty")));
    }

    @Test
    public void testValidateAdd_Fail_NameFieldIsAbsent() {
        Mockito.when(rules.validateName(null))
                .thenReturn(Optional.of(new CoreError("Name",  "Field should not be empty")));

        AddRequest request = new AddRequest(null, "description", "1.10");
        List<CoreError> errors = addValidator.validate(request);
        assertTrue(errors.size() == 1);
        assertTrue(errors.stream().anyMatch(s -> s.getField().equals("Name")));
        assertTrue(errors.stream().anyMatch(s -> s.getMessage().equals("Field should not be empty")));
    }

    @Test
    public void testValidateAdd_Fail_NameFieldIsEmpty() {
        Mockito.when(rules.validateName(""))
                .thenReturn(Optional.of(new CoreError("Name",  "Field should not be empty")));

        AddRequest request = new AddRequest("", "description", "1.10");
        List<CoreError> errors = addValidator.validate(request);
        assertTrue(errors.size() == 1);
        assertTrue(errors.stream().anyMatch(s -> s.getField().equals("Name")));
        assertTrue(errors.stream().anyMatch(s -> s.getMessage().equals("Field should not be empty")));
    }

    @Test
    public void testValidateAdd_Fail_DescriptionFieldIsAbsent() {
        Mockito.when(rules.validateDescription( null))
                .thenReturn(Optional.of(new CoreError("Description",  "Field should not be empty")));

        AddRequest request = new AddRequest("pen", null, "1.10");
        List<CoreError> errors = addValidator.validate(request);
        assertTrue(errors.size() == 1);
        assertTrue(errors.stream().anyMatch(s -> s.getField().equals("Description")));
        assertTrue(errors.stream().anyMatch(s -> s.getMessage().equals("Field should not be empty")));
    }

    @Test
    public void testValidateAdd_Fail_DescriptionFieldIsEmpty() {
        Mockito.when(rules.validateDescription(""))
                .thenReturn(Optional.of(new CoreError("Description",  "Field should not be empty")));

        AddRequest request = new AddRequest("pen", "", "1.10");
        List<CoreError> errors = addValidator.validate(request);
        assertTrue(errors.size() == 1);
        assertTrue(errors.stream().anyMatch(s -> s.getField().equals("Description") && s.getMessage().equals("Field should not be empty")));
    }

    @Test
    public void testValidateAdd_Fail_PriceFieldIsAbsent() {
        Mockito.when(rules.validatePrice(null))
                .thenReturn(Optional.of(new CoreError("Price",  "Field should not be empty")));

        AddRequest request = new AddRequest("name", "description", null);
        List<CoreError> errors = addValidator.validate(request);
        assertTrue(errors.size() == 1);
        assertTrue(errors.stream().anyMatch(s -> s.getField().equals("Price")));
        assertTrue(errors.stream().anyMatch(s -> s.getMessage().equals("Field should not be empty")));
    }

    @Test
    public void testValidateAdd_Fail_PriceFieldIsEmpty() {
        Mockito.when(rules.validatePrice(""))
                .thenReturn(Optional.of(new CoreError("Price",  "Field should not be empty")));

        AddRequest request = new AddRequest("name", "description", "");
        List<CoreError> errors = addValidator.validate(request);
        assertTrue(errors.size() == 1);
        assertTrue(errors.stream().anyMatch(s -> s.getField().equals("Price")));
        assertTrue(errors.stream().anyMatch(s -> s.getMessage().equals("Field should not be empty")));
    }

    @Test
    public void testValidateAdd_Fail_InvalidPriceField() {
        Mockito.when(rules.validatePrice("XYZ"))
                .thenReturn(Optional.of(new CoreError("Price",  "Field should be valid")));

        AddRequest request = new AddRequest("name", "description", "XYZ");
        List<CoreError> errors = addValidator.validate(request);
        assertTrue(errors.size() == 1);
        assertTrue(errors.stream().anyMatch(s -> s.getField().equals("Price")));
        assertTrue(errors.stream().anyMatch(s -> s.getMessage().equals("Field should be valid")));
    }
}