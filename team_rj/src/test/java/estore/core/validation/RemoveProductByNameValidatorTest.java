package estore.core.validation;

import estore.core.requests.RemoveProductByNameRequest;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class RemoveProductByNameValidatorTest {

    private ValidationRules validationRules = new ValidationRules();
    private RemoveProductByNameValidator validator = new RemoveProductByNameValidator(validationRules);

    @Test
    public void shouldNotReturnErrorIfProductNameIsProvided() {
        RemoveProductByNameRequest request = new RemoveProductByNameRequest("ValidName");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorIfProductNameHasDigits() {
        RemoveProductByNameRequest request = new RemoveProductByNameRequest("Product1");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product Name");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
    }

    @Test
    public void shouldReturnErrorIfProductNameHasWhiteSpaces() {
        RemoveProductByNameRequest request = new RemoveProductByNameRequest("Invalid Product");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product Name");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
    }

}