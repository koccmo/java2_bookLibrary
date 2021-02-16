package lv.estore.app.core.validators;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.products.UpdateProductByIdRequest;
import lv.estore.app.core.validators.common_validation_rules.ValidationRules;
import lv.estore.app.core.validators.products.ProductUpdateValidator;
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
public class ProductUpdateValidatorTest {

    @Mock
    ValidationRules rules;

    @InjectMocks
    ProductUpdateValidator productUpdateValidator;

    @Test
    public void testValidate_AllValid() {
        Mockito.when(rules.validateId("productId", "1")).thenReturn(Optional.empty());
        Mockito.when(rules.validateTextField("productName", "pen")).thenReturn(Optional.empty());
        Mockito.when(rules.validateTextField("productDescription", "red")).thenReturn(Optional.empty());
        Mockito.when(rules.validatePrice("1.0")).thenReturn(Optional.empty());

        UpdateProductByIdRequest request = new UpdateProductByIdRequest("1", "pen", "red", "1.0");
        List<CoreError> errors = productUpdateValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testValidate_AllAbsent() {
        Mockito.when(rules.validateId("productId",null)).thenReturn(Optional.of(new CoreError("productId", "Field should not be empty")));
        Mockito.when(rules.validateTextField("productName", null)).thenReturn(Optional.of(new CoreError("productName", "Field should not be empty")));
        Mockito.when(rules.validateTextField("productDescription", null)).thenReturn(Optional.of(new CoreError("productDescription", "Field should not be empty")));
        Mockito.when(rules.validatePrice(null)).thenReturn(Optional.of(new CoreError("productPrice", "Field should not be empty")));

        UpdateProductByIdRequest request = new UpdateProductByIdRequest(null, null, null, null);
        List<CoreError> errors = productUpdateValidator.validate(request);
        assertEquals(4, errors.size());
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("productId") && s.getMessage().equals("Field should not be empty")));
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("productName") && s.getMessage().equals("Field should not be empty")));
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("productDescription") && s.getMessage().equals("Field should not be empty")));
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("productPrice") && s.getMessage().equals("Field should not be empty")));
    }

    @Test
    public void testValidate_Invalid() {
        Mockito.when(rules.validateId("productId", "-1")).thenReturn(Optional.of(new CoreError("productId", "Value should be valid")));
        Mockito.when(rules.validatePrice("XYZ")).thenReturn(Optional.of(new CoreError("productPrice", "Value should be valid")));

        UpdateProductByIdRequest request = new UpdateProductByIdRequest("-1", "pen", "red", "XYZ");
        List<CoreError> errors = productUpdateValidator.validate(request);
        assertEquals("Errors size = " + errors.size(), 2, errors.size());
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("productId") && s.getMessage().equals("Value should be valid")));
        assertTrue(errors.stream()
                .anyMatch(s -> s.getField().equals("productPrice") && s.getMessage().equals("Value should be valid")));
    }
}