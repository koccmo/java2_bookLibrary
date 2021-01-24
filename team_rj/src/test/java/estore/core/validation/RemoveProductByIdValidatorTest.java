package estore.core.validation;

import estore.core.requests.RemoveProductByIdRequest;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RemoveProductByIdValidatorTest {

    private ValidationRules validationRules = new ValidationRules();
    private RemoveProductByIdValidator validator = new RemoveProductByIdValidator(validationRules);

    @Test
    public void shouldReturnErrorIfProductIdIsNull() {
        RemoveProductByIdRequest request = new RemoveProductByIdRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "Product ID");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
        assertEquals(errors.get(1).getField(), "Product ID");
        assertEquals(errors.get(1).getMessage(), "Must contain only digits");
    }

    @Test
    public void shouldReturnErrorIfProductIdHasUnallowedPattern() {
        RemoveProductByIdRequest request = new RemoveProductByIdRequest("First");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product ID");
        assertEquals(errors.get(0).getMessage(), "Must contain only digits");
    }
}