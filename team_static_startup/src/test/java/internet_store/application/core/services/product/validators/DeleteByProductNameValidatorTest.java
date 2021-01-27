package internet_store.application.core.services.product.validators;

import internet_store.application.core.requests.product.DeleteByProductNameRequest;
import internet_store.application.core.responses.CoreError;
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
        assertEquals("Product name", errors.get(0).getField());
        assertEquals("must not be empty", errors.get(0).getMessage());
    }

}