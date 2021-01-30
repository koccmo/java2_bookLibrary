package estore.core.service;

import estore.core.requests.AddProductCategoryRequest;
import estore.core.responses.AddProductCategoryResponse;
import estore.core.validation.AddProductCategoryValidator;
import estore.core.validation.CoreError;
import estore.database.ProductCategoryRepository;
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
public class AddNewProductCategoryServiceTest {

    @Mock
    private ProductCategoryRepository database;
    @Mock
    private AddProductCategoryValidator validator;

    @InjectMocks
    private AddProductCategoryService service;

    @Test
    public void shouldReturnResponseWithErrorsIfValidationFails() {
        AddProductCategoryRequest request = new AddProductCategoryRequest("NewCategory");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Product category", "Must contain only english letters!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddProductCategoryResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Product category");
        assertEquals(response.getErrors().get(0).getMessage(), "Must contain only english letters!");
    }

    @Test
    public void shouldAddNewCategoryToDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddProductCategoryRequest request = new AddProductCategoryRequest("NewCategory");
        AddProductCategoryResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        //Mockito.verify(database).addNewCategory(argThat(new ProductCategoryMatcher("NewCategory")));
    }
}