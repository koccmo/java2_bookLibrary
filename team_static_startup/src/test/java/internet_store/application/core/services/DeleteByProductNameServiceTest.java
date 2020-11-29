package internet_store.application.core.services;

import internet_store.application.core.requests.DeleteByProductNameRequest;
import internet_store.application.core.responses.CoreError;
import internet_store.application.core.responses.DeleteByProductNameResponse;
import internet_store.application.core.services.validators.DeleteByProductNameValidator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DeleteByProductNameServiceTest {

    private DeleteByProductNameService service;

    @Before
    public void setup() {
        DeleteByProductNameValidator validatorMock = new DeleteByProductNameValidatorMock();
        service = new DeleteByProductNameService(null, validatorMock);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidatorFails() {
        DeleteByProductNameRequest request = new DeleteByProductNameRequest(null);
        DeleteByProductNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Product Name");
    }

    class DeleteByProductNameValidatorMock extends DeleteByProductNameValidator {

        public List<CoreError> validate(DeleteByProductNameRequest request) {
            List<CoreError> errors = new ArrayList<>();
            errors.add(new CoreError("Product Name", "Product Name must not be empty."));
            return errors;
        }
    }

}