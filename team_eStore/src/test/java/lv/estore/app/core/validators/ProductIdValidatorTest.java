package lv.estore.app.core.validators;

import lv.estore.app.core.errors.CoreError;
import lv.estore.app.core.request.products.ProductIdRequest;
import lv.estore.app.core.validators.products.ProductIdValidator;
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
public class ProductIdValidatorTest {

    @Mock
    ValidationRules rules;
    @InjectMocks
    ProductIdValidator productIdValidator;

    @Test
    public void testValidateId_Successful() {
        Mockito.when(rules.validateId(any(), any())).thenReturn(Optional.empty());

        ProductIdRequest request = new ProductIdRequest("1");
        List<CoreError> errors = productIdValidator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void testValidateId_IdAbsent() {
        Mockito.when(rules.validateId(any(), any())).thenReturn(Optional.of(new CoreError("Id",  "Field should not be empty")));

        ProductIdRequest request = new ProductIdRequest(null);
        List<CoreError> errors = productIdValidator.validate(request);
        assertEquals(1, errors.size());
        assertTrue(errors.stream().anyMatch(s-> "Id".equals(s.getField())));
        assertTrue(errors.stream().anyMatch(s-> "Field should not be empty".equals(s.getMessage())));
    }

    @Test
    public void testValidateId_IdEmpty() {
        Mockito.when(rules.validateId(any(), any())).thenReturn(Optional.of(new CoreError("Id",  "Field should not be empty")));

        ProductIdRequest request = new ProductIdRequest("");
        List<CoreError> errors = productIdValidator.validate(request);
        assertEquals(1, errors.size());
        assertTrue(errors.stream().anyMatch(s-> "Id".equals(s.getField())));
        assertTrue(errors.stream().anyMatch(s-> "Field should not be empty".equals(s.getMessage())));
    }

    @Test
    public void testValidateId_Invalid() {
        Mockito.when(rules.validateId(any(), any())).thenReturn(Optional.of(new CoreError("Id",  "Value should be valid")));

        ProductIdRequest request = new ProductIdRequest("-1");
        List<CoreError> errors = productIdValidator.validate(request);
        assertEquals(1, errors.size());
        assertTrue(errors.stream().anyMatch(s-> "Id".equals(s.getField())));
        assertTrue(errors.stream().anyMatch(s-> "Value should be valid".equals(s.getMessage())));
    }
}