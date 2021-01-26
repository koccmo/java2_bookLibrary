package internet_store.application.core.services.validators;

import internet_store.application.core.requests.product.DeleteByProductIdRequest;
import internet_store.application.core.responses.product.CoreError;
import internet_store.application.core.services.product.validators.DeleteByProductIdValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DeleteByProductIdValidatorTest {

    private DeleteByProductIdValidator validator;

    @Before

    public void setUp () {
        validator = new DeleteByProductIdValidator();
    }

    @Test
    public void shouldNotPassWhenIdIsNull() {
        DeleteByProductIdRequest request = new DeleteByProductIdRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Product ID", errors.get(0).getField());
        assertEquals("Should not be empty.", errors.get(0).getMessage());
    }

    @Test
    public void shouldNotReturnErrorWhenIdIsNotNull() {
        DeleteByProductIdRequest request = new DeleteByProductIdRequest(1L);
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }



}