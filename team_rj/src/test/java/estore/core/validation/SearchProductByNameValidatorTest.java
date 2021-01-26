package estore.core.validation;

import estore.core.requests.SearchProductByNameRequest;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SearchProductByNameValidatorTest {

    private ValidationRules validationRules = new ValidationRules();
    private SearchProductByNameValidator validator = new SearchProductByNameValidator(validationRules);

    @Test
    public void shouldNotReturnErrorIfProductNameIsProvided() {
        SearchProductByNameRequest request = new SearchProductByNameRequest("ValidName");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorIfProductNameHasDigits() {
        SearchProductByNameRequest request = new SearchProductByNameRequest("Product1");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product Name");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
    }

    @Test
    public void shouldReturnErrorIfProductNameHasWhiteSpaces() {
        SearchProductByNameRequest request = new SearchProductByNameRequest("Invalid Product");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product Name");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
    }

}