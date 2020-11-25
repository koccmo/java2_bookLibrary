package internet_store.core.validate;

import internet_store.core.core_error.CoreError;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class ProductQuantityValidatorTest {

    ProductQuantityValidator validator = new ProductQuantityValidator();

    @Test
    public void shouldReturnTrue_QuantityMoreZero() {
        List<CoreError> errors = validator.validate(new BigDecimal("5"), new BigDecimal("1"));
        assertTrue(errors.isEmpty());
    }

    @Test
    public void shouldReturnError_QuantityIsZero() {
        List<CoreError> errors = validator.validate(new BigDecimal("0"), new BigDecimal("0"));
        assertFalse(errors.isEmpty());
        assertEquals("Add to cart error ", errors.get(0).getField());
        assertEquals("Product quantity is zero", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnError_QuantityIsZero_1() {
        List<CoreError> errors = validator.validate(new BigDecimal("0"), new BigDecimal("5"));
        assertFalse(errors.isEmpty());
        assertEquals("Add to cart error ", errors.get(0).getField());
        assertEquals("Product quantity is zero", errors.get(0).getMessage());
        assertEquals("Quantity error ", errors.get(1).getField());
        assertEquals("No more product's quantity", errors.get(1).getMessage());
    }

    @Test
    public void shouldReturnError_QuantityIsZero_2() {
        List<CoreError> errors = validator.validate(new BigDecimal("5"), new BigDecimal("15"));
        assertFalse(errors.isEmpty());
        assertEquals("Quantity error ", errors.get(0).getField());
        assertEquals("No more product's quantity", errors.get(0).getMessage());
    }
}