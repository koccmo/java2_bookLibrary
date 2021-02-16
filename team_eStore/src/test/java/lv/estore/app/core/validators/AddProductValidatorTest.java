package lv.estore.app.core.validators;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.products.AddProductRequest;
import lv.estore.app.core.validators.common_validation_rules.ValidationRules;
import lv.estore.app.core.validators.products.AddProductValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class AddProductValidatorTest {

    @Mock
    ValidationRules rules;

    @InjectMocks
    AddProductValidator addProductValidator;

    @Test
    public void testValidateAdd_Success_AllFieldsAreUsed() {
        Mockito.when(rules.validateTextField("productName", "pen")).thenReturn(Optional.empty());
        Mockito.when(rules.validateTextField("productDescription", "description")).thenReturn(Optional.empty());
        Mockito.when(rules.validatePrice("1.10")).thenReturn(Optional.empty());
        AddProductRequest request = new AddProductRequest("pen", "description", "1.10");
        List<CoreError> errors = addProductValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testValidateAdd_Fail_AllFieldAbsent() {
        Mockito.when(rules.validateTextField("productName", null))
                .thenReturn(Optional.of(new CoreError("productName",  "Field should not be empty")));
        Mockito.when(rules.validateTextField("productDescription",null))
                .thenReturn(Optional.of(new CoreError("productDescription",  "Field should not be empty")));
        Mockito.when(rules.validatePrice(null))
                .thenReturn(Optional.of(new CoreError("productPrice",  "Field should not be empty")));

        AddProductRequest request = new AddProductRequest(null, null, null);
        List<CoreError> errors = addProductValidator.validate(request);
        assertEquals(3, errors.size());
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("productName") && s.getMessage().equals("Field should not be empty")));
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("productDescription") && s.getMessage().equals("Field should not be empty")));
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("productPrice") && s.getMessage().equals("Field should not be empty")));
    }

    @Test
    public void testValidateAdd_Fail_NameFieldIsAbsent() {
        Mockito.when(rules.validateTextField("productName",null))
                .thenReturn(Optional.of(new CoreError("productName",  "Field should not be empty")));

        AddProductRequest request = new AddProductRequest(null, "description", "1.10");
        List<CoreError> errors = addProductValidator.validate(request);
        assertEquals(1, errors.size());
        assertTrue(errors.stream().anyMatch(s -> s.getField().equals("productName")));
        assertTrue(errors.stream().anyMatch(s -> s.getMessage().equals("Field should not be empty")));
    }

    @Test
    public void testValidateAdd_Fail_NameFieldIsEmpty() {
        Mockito.when(rules.validateTextField("productName", ""))
                .thenReturn(Optional.of(new CoreError("productName",  "Field should not be empty")));

        AddProductRequest request = new AddProductRequest("", "description", "1.10");
        List<CoreError> errors = addProductValidator.validate(request);
        assertEquals(1, errors.size());
        assertTrue(errors.stream().anyMatch(s -> s.getField().equals("productName")));
        assertTrue(errors.stream().anyMatch(s -> s.getMessage().equals("Field should not be empty")));
    }

    @Test
    public void testValidateAdd_Fail_DescriptionFieldIsAbsent() {
        Mockito.when(rules.validateTextField("productDescription", null))
                .thenReturn(Optional.of(new CoreError("productDescription",  "Field should not be empty")));

        AddProductRequest request = new AddProductRequest("pen", null, "1.10");
        List<CoreError> errors = addProductValidator.validate(request);
        assertEquals(1, errors.size());
        assertTrue(errors.stream().anyMatch(s -> s.getField().equals("productDescription")));
        assertTrue(errors.stream().anyMatch(s -> s.getMessage().equals("Field should not be empty")));
    }

    @Test
    public void testValidateAdd_Fail_DescriptionFieldIsEmpty() {
        Mockito.when(rules.validateTextField("productDescription",""))
                .thenReturn(Optional.of(new CoreError("productDescription",  "Field should not be empty")));

        AddProductRequest request = new AddProductRequest("pen", "", "1.10");
        List<CoreError> errors = addProductValidator.validate(request);
        assertEquals(1, errors.size());
        assertTrue(errors.stream().anyMatch(s -> s.getField().equals("productDescription") && s.getMessage().equals("Field should not be empty")));
    }

    @Test
    public void testValidateAdd_Fail_PriceFieldIsAbsent() {
        Mockito.when(rules.validatePrice(null))
                .thenReturn(Optional.of(new CoreError("productPrice",  "Field should not be empty")));

        AddProductRequest request = new AddProductRequest("name", "description", null);
        List<CoreError> errors = addProductValidator.validate(request);
        assertEquals(1, errors.size());
        assertTrue(errors.stream().anyMatch(s -> s.getField().equals("productPrice")));
        assertTrue(errors.stream().anyMatch(s -> s.getMessage().equals("Field should not be empty")));
    }

    @Test
    public void testValidateAdd_Fail_PriceFieldIsEmpty() {
        Mockito.when(rules.validatePrice(""))
                .thenReturn(Optional.of(new CoreError("productPrice",  "Field should not be empty")));

        AddProductRequest request = new AddProductRequest("name", "description", "");
        List<CoreError> errors = addProductValidator.validate(request);
        assertEquals(1, errors.size());
        assertTrue(errors.stream().anyMatch(s -> s.getField().equals("productPrice")));
        assertTrue(errors.stream().anyMatch(s -> s.getMessage().equals("Field should not be empty")));
    }

    @Test
    public void testValidateAdd_Fail_InvalidPriceField() {
        Mockito.when(rules.validatePrice("XYZ"))
                .thenReturn(Optional.of(new CoreError("productPrice",  "Field should be valid")));

        AddProductRequest request = new AddProductRequest("name", "description", "XYZ");
        List<CoreError> errors = addProductValidator.validate(request);
        assertEquals(1, errors.size());
        assertTrue(errors.stream().anyMatch(s -> s.getField().equals("productPrice")));
        assertTrue(errors.stream().anyMatch(s -> s.getMessage().equals("Field should be valid")));
    }
}