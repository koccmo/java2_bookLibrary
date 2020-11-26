package lesson_3.core.validate;

/*

import lesson_3.core.core_error.CoreError;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NegativeNumberValidatorTest {
    @Test
    public void shouldReturnNoError_NumberPositive_1() {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>(55);
        List<CoreError> errors = negativeNumberValidator.validate();
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnNoError_NumberPositive_2() {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>(new BigDecimal("105"));
        List<CoreError> errors = negativeNumberValidator.validate();
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnNoError_NumberPositive_3() {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>(55L);
        List<CoreError> errors = negativeNumberValidator.validate();
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnNoError_NumberZero_1() {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>(0);
        List<CoreError> errors = negativeNumberValidator.validate();
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnNoError_NumberZero_2() {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>(new BigDecimal("0"));
        List<CoreError> errors = negativeNumberValidator.validate();
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnError_NumberNegative_1() {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>(-100);
        List<CoreError> errors = negativeNumberValidator.validate();
        assertEquals(1, errors.size());
        assertEquals("Integer input error ", errors.get(0).getField());
        assertEquals("only positive number allowed", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnError_NumberNegative_2() {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>(new BigDecimal("-189"));
        List<CoreError> errors = negativeNumberValidator.validate();
        assertEquals(1, errors.size());
        assertEquals("BigDecimal input error ", errors.get(0).getField());
        assertEquals("only positive number allowed", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnError_NumberNegative_3() {
        NegativeNumberValidator<?> negativeNumberValidator = new NegativeNumberValidator<>(-100L);
        List<CoreError> errors = negativeNumberValidator.validate();
        assertEquals(1, errors.size());
        assertEquals("Long input error ", errors.get(0).getField());
        assertEquals("only positive number allowed", errors.get(0).getMessage());
    }
}*/
