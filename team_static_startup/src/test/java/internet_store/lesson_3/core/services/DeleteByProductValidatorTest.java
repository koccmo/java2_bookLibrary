package internet_store.lesson_3.core.services;

import internet_store.lesson_3.core.requests.DeleteByProductRequest;
import internet_store.lesson_3.core.responses.CoreError;
import internet_store.lesson_3.core.services.validators.DeleteByProductValidator;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DeleteByProductValidatorTest {

    private DeleteByProductValidator validator;

    @Before
    public void setUp() {
        validator = new DeleteByProductValidator();
    }

    @Test
    public void shouldNotPassProductName_whenItIsNull() {
        List<CoreError> result = validator.validate(new DeleteByProductRequest(null,
                "description",
                new BigDecimal("10.00")));
        assertEquals(1, result.size());
        assertEquals("Name", result.get(0).getField());
    }

    @Test
    public void shouldNotPassProductName_whenItIsEmpty() {
        List<CoreError> result = validator.validate(new DeleteByProductRequest("",
                "description",
                new BigDecimal("10.00")));
        assertEquals(1, result.size());
        assertEquals("Name", result.get(0).getField());
    }

    @Test
    public void shouldNotPassProductDescription_whenItIsNull() {
        List<CoreError> result = validator.validate(new DeleteByProductRequest("name",
                null,
                new BigDecimal("10.00")));
        assertEquals(1, result.size());
        assertEquals("Description", result.get(0).getField());
    }

    @Test
    public void shouldNotPassProductDescription_whenItIsEmpty() {
        List<CoreError> result = validator.validate(new DeleteByProductRequest("name",
                "",
                new BigDecimal("10.00")));
        assertEquals(1, result.size());
        assertEquals("Description", result.get(0).getField());
    }

}