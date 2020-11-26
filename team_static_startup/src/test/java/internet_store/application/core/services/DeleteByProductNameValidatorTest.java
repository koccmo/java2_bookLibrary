package internet_store.application.core.services;

import internet_store.application.core.requests.DeleteByProductNameRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.services.validators.DeleteByProductNameValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DeleteByProductNameValidatorTest {

    private DeleteByProductNameValidator validator;

    @Before
    public void setUp(){
        validator = new DeleteByProductNameValidator();
    }

    @Test
    public void success() {
        DeleteByProductNameRequest request = new DeleteByProductNameRequest("Product");
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnErrorWhenProductNameIsNull() {
        DeleteByProductNameRequest request = new DeleteByProductNameRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Product Name", errors.get(0).getField());
        assertEquals("Product Name must not be empty.", errors.get(0).getMessage());
    }
}