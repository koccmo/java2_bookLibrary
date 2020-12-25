package estore.core.service;

import estore.core.requests.AddNewProductCategoryRequest;
import estore.core.responses.AddNewProductCategoryResponse;
import estore.core.validation.AddNewProductCategoryValidator;
import estore.core.validation.CoreError;
import estore.database.ProductCategoryDB;
import estore.matchers.ProductCategoryMatcher;
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
public class AddNewProductCategoryServiceTest {

    @Mock
    private ProductCategoryDB database;
    @Mock
    private AddNewProductCategoryValidator validator;

    @InjectMocks
    private AddNewProductCategoryService service;

    @Test
    public void shouldReturnResponseWithErrorsIfValidationFails() {
        AddNewProductCategoryRequest request = new AddNewProductCategoryRequest("NewCategory");
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("Product category", "Must contain only english letters!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddNewProductCategoryResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 1);
        assertEquals(response.getErrors().get(0).getField(), "Product category");
        assertEquals(response.getErrors().get(0).getMessage(), "Must contain only english letters!");
    }

    @Test
    public void shouldAddNewCategoryToDatabase() {
        Mockito.when(validator.validate(any())).thenReturn(new ArrayList<>());
        AddNewProductCategoryRequest request = new AddNewProductCategoryRequest("NewCategory");
        AddNewProductCategoryResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        //Mockito.verify(database).addNewCategory(argThat(new ProductCategoryMatcher("NewCategory")));
    }
}