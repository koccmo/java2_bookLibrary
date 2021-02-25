package internet_store.application.core.services.product.validators;

import internet_store.application.core.requests.product.UpdateProductRequest;
import internet_store.application.core.responses.CoreError;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UpdateProductValidatorTest {

    private UpdateProductValidator validator;

    @Before
    public void setUp() {
        validator = new UpdateProductValidator();
    }

    @Test
    public void shouldReturnNoErrors() {
        UpdateProductRequest request = new UpdateProductRequest(
                1L, "name", "description", new BigDecimal("1.00"));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenNameIsNull() {
        UpdateProductRequest request = new UpdateProductRequest(
                1L, null, "description", new BigDecimal("1.00"));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "newName");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenDescriptionIsNull() {
        UpdateProductRequest request = new UpdateProductRequest(
                1L, "name", null, new BigDecimal("1.00"));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "newDescription");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPriceIsNull() {
        UpdateProductRequest request = new UpdateProductRequest(
                1L, "name", "description", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "newPrice");
        assertEquals(errors.get(0).getMessage(), "Must not be empty or less than 0!");
    }

    @Test
    public void shouldReturnErrorWhenPriceIsLessThanZero() {
        UpdateProductRequest request = new UpdateProductRequest(
                1L, "name", "description", new BigDecimal("-1.00"));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "newPrice");
        assertEquals(errors.get(0).getMessage(), "Must not be empty or less than 0!");
    }

}