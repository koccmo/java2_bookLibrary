package estore.core.service;

import estore.core.requests.RemoveProductByNameRequest;
import estore.core.responses.RemoveProductByNameResponse;
import estore.core.validation.CoreError;
import estore.core.validation.RemoveProductByNameValidator;
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
public class RemoveProductByNameServiceTest {

    @Mock
    private ProductRepository database;
    @Mock
    private RemoveProductByNameValidator validator;

    @InjectMocks
    private RemoveProductByNameService service;

    @Test
    public void shouldReturnResponseWithErrorsIfValidationFails() {
        RemoveProductByNameRequest request = new RemoveProductByNameRequest(null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Product Name", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        RemoveProductByNameResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Product Name");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldRemoveProductIfNameIsProvided() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        Mockito.when(database.removeProductByName("ProductName")).thenReturn(1);
        RemoveProductByNameRequest request = new RemoveProductByNameRequest("ProductName");
        RemoveProductByNameResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProductsRemoved(), 1);

        request = new RemoveProductByNameRequest("Product_2");
        response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProductsRemoved(), 0);
    }
}