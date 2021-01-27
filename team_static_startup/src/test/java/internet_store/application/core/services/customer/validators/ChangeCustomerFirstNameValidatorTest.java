package internet_store.application.core.services.customer.validators;

import internet_store.application.core.requests.customer.ChangeCustomerFirstNameRequest;
import internet_store.application.core.responses.CoreError;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ChangeCustomerFirstNameValidatorTest {

    ChangeCustomerFirstNameValidator validator;

    @Before
    public void setUp(){
        validator = new ChangeCustomerFirstNameValidator();
    }

    @Test
    public void shouldReturnNoErrors() {
        ChangeCustomerFirstNameRequest request = new ChangeCustomerFirstNameRequest(1L, "newName");
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnErrorWhenNameIsNull() {
        ChangeCustomerFirstNameRequest request = new ChangeCustomerFirstNameRequest(1L, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Customer Name");
        assertEquals(errors.get(0).getMessage(), "Should not be empty");
    }
    @Test
    public void shouldReturnErrorWhenNameIsEmpty() {
        ChangeCustomerFirstNameRequest request = new ChangeCustomerFirstNameRequest(1L, "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Customer Name");
        assertEquals(errors.get(0).getMessage(), "Should not be empty");
    }

}