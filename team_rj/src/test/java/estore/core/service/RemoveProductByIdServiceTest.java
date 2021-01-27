package estore.core.service;

import estore.core.requests.RemoveProductByIdRequest;
import estore.core.responses.RemoveProductByIdResponse;
import estore.core.validation.CoreError;
import estore.core.validation.RemoveProductByIdValidator;
import estore.database.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class RemoveProductByIdServiceTest {

    @Mock
    private ProductRepository database;
    @Mock
    private RemoveProductByIdValidator validator;

    @InjectMocks
    private RemoveProductByIdService service;

    @Test
    public void shouldReturnResponseWithErrorsIfValidationFails() {
        RemoveProductByIdRequest request = new RemoveProductByIdRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Product ID", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        RemoveProductByIdResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Product ID");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldRemoveProductIfIdIsProvided() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.removeProductById(1L)).thenReturn(1);
        RemoveProductByIdRequest request = new RemoveProductByIdRequest("1");
        RemoveProductByIdResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProductsRemoved(), 1);

        request = new RemoveProductByIdRequest("10");
        response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProductsRemoved(), 0);
    }
}