package internet_store.application.core.services;

import internet_store.application.core.requests.DeleteByProductNameRequest;
import internet_store.application.core.responses.*;
import internet_store.application.core.services.validators.DeleteByProductNameValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DeleteByProductNameServiceTest {

    private DeleteByProductNameValidator validator;
    private DeleteByProductNameService service;

    @Before
    public void setup() {
        validator = Mockito.mock(DeleteByProductNameValidator.class);
        service = new DeleteByProductNameService(null, validator);
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidatorFails() {
        DeleteByProductNameRequest request = new DeleteByProductNameRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Product Name", "Product Name must not be empty."));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        DeleteByProductNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Product Name");
    }

}