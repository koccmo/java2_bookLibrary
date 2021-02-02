package estore.core.service;

import estore.core.requests.AddProductRequest;
import estore.core.responses.AddProductResponse;
import estore.core.validation.AddProductValidator;
import estore.core.validation.CoreError;
import estore.database.ProductCategoryRepository;
import estore.database.ProductRepository;
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
public class AddProductServiceTest {

    @Mock
    private ProductRepository database;
    @Mock
    private AddProductValidator validator;
    @Mock
    private ProductCategoryRepository categoryRepository;

    @InjectMocks
    private AddProductService service;

    @Test
    public void shouldReturnResponseWithErrorsIfValidationFails() {
        AddProductRequest request = new AddProductRequest(null, "Description", "Fruits");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Product name", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddProductResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Product name");
        assertEquals(response.getErrors().get(0).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldAddNewProductToDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddProductRequest request = new AddProductRequest("Name", "Description", "Category");
        AddProductResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getProduct().getName(), "Name");
        assertEquals(response.getProduct().getCategory().getCategory(), "Category");

        Mockito.verify(database)
                .addNewProduct(argThat(new ProductMatcher("Name", "Description", "Category", 0, 0.0)));
    }
}