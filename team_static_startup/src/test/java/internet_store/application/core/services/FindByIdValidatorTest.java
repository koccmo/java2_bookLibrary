package internet_store.application.core.services;


import internet_store.application.core.requests.FindByIdRequest;
import internet_store.application.core.responses.CoreError;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FindByIdValidatorTest {

    private FindByIdValidator validator;

    @Before

    public void setUp () {
        validator = new FindByIdValidator();
    }

    @Test
    public void shallNotPassWhenIdIsNull() {
        FindByIdRequest request = new FindByIdRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Product ID", errors.get(0).getField());
        assertEquals("Should not be empty.", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnNoErrorWhenIdIsNotNull() {
        FindByIdRequest request = new FindByIdRequest(1L);
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

}