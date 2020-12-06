package estore.core.service;

import estore.core.requests.AddNewProductRequest;
import estore.core.responses.AddNewProductResponse;
import estore.core.validation.AddNewProductValidator;
import estore.core.validation.CoreError;
import estore.database.ProductDB;
import estore.matchers.ProductMatcher;
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
import static org.mockito.ArgumentMatchers.argThat;

@RunWith(MockitoJUnitRunner.class)
public class AddNewProductServiceTest {

    @Mock
    private ProductDB database;
    @Mock
    private AddNewProductValidator validator;

    @InjectMocks
    private AddNewProductService service;

    @Test
    public void shouldReturnResponseWithErrorsIfValidationFails() {
        AddNewProductRequest request = new AddNewProductRequest(null, "Description", "Fruits");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Product name", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddNewProductResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Product name");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldAddNewProductToDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddNewProductRequest request = new AddNewProductRequest("Name", "Description", "Fruits");
        AddNewProductResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        Mockito.verify(database)
                .addNewProduct(argThat(new ProductMatcher("Name", "Description", "Fruits")));
    }
}