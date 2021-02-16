package lv.estore.app.core.validators;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.products.ProductNameRequest;
import lv.estore.app.core.validators.products.ProductNameValidator;
import lv.estore.app.core.validators.common_validation_rules.ValidationRules;
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
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ProductNameValidatorTest {

    @Mock
    ValidationRules rules;
    @InjectMocks
    ProductNameValidator validator;

    @Test
    public void testValidateName_Successful() {
        Mockito.when(rules.validateTextField(any(), any())).thenReturn(Optional.empty());

        ProductNameRequest request = new ProductNameRequest("Name");
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testValidateName_Absent() {
        Mockito.when(rules.validateTextField(any(), any()))
                .thenReturn(Optional.of(new CoreError("Name",  "Field should not be empty")));

        ProductNameRequest request = new ProductNameRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertTrue(errors.stream().anyMatch(s-> "Name".equals(s.getField())));
        assertTrue(errors.stream().anyMatch(s-> "Field should not be empty".equals(s.getMessage())));
    }

    @Test
    public void testValidateName_Empty() {
        Mockito.when(rules.validateTextField(any(), any()))
                .thenReturn(Optional.of(new CoreError("Name",  "Field should not be empty")));

        ProductNameRequest request = new ProductNameRequest("");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertTrue(errors.stream().anyMatch(s-> "Name".equals(s.getField())));
        assertTrue(errors.stream().anyMatch(s-> "Field should not be empty".equals(s.getMessage())));
    }

    @Test
    public void testValidateName_Invalid() {
        Mockito.when(rules.validateTextField(any(), any()))
                .thenReturn(Optional.of(new CoreError("Name",  "Field should be valid")));

        ProductNameRequest request = new ProductNameRequest("123dos");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertTrue(errors.stream().anyMatch(s-> "Name".equals(s.getField())));
        assertTrue(errors.stream().anyMatch(s-> "Field should be valid".equals(s.getMessage())));
    }
}