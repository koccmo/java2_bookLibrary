package internet_store.application.core.services.customer.validators;

import internet_store.application.core.requests.customer.FindByCustomerIdRequest;
import internet_store.application.core.responses.CoreError;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class FindByCustomerIdValidatorTest {

    FindByCustomerIdValidator validator;

    @Before
    public void setUp () {
        validator = new FindByCustomerIdValidator();
    }

    @Test
    public void shallNotPassWhenIdIsNull() {
        FindByCustomerIdRequest request = new FindByCustomerIdRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals(1, errors.size());
        assertEquals("Customer Id", errors.get(0).getField());
        assertEquals("Should not be empty", errors.get(0).getMessage());
    }

    @Test
    public void shallNotPassWhenIdIsEmpty() {
        FindByCustomerIdRequest request = new FindByCustomerIdRequest(" ");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Customer Id", errors.get(0).getField());
        assertEquals("Should not be empty", errors.get(0).getMessage());
    }

    @Test
    public void shallNotPassWhenIdIsNotNumber() {
        FindByCustomerIdRequest request = new FindByCustomerIdRequest("MISTAKE");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Customer Id", errors.get(0).getField());
        assertEquals("Should be valid.", errors.get(0).getMessage());
    }

}