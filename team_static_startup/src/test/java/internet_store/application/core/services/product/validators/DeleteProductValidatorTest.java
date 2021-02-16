package internet_store.application.core.services.product.validators;

import internet_store.application.core.requests.product.DeleteProductRequest;
import internet_store.application.core.responses.CoreError;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class DeleteProductValidatorTest {

    private DeleteProductValidator validator;

    @Before
    public void setUp() {
        validator = new DeleteProductValidator();
    }

    @Test
    public void shouldReturnNoErrors() {
        DeleteProductRequest request = new DeleteProductRequest(1L);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenIdIsNull() {
        DeleteProductRequest request = new DeleteProductRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "id");
        assertEquals(errors.get(0).getMessage(), "must not be empty!");
    }

}