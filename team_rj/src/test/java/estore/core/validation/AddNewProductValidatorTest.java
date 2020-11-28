package estore.core.validation;

import estore.core.requests.AddNewProductRequest;
import estore.core.requests.SearchProductByCategoryRequest;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddNewProductValidatorTest {

    private AddNewProductValidator validator = new AddNewProductValidator();

    @Test
    public void shouldNotReturnErrorIfProductNameIsProvided() {
        AddNewProductRequest request = new AddNewProductRequest("ValidName",
                "Valid Description", "Fruits");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorIfProductDescriptionIsProvided() {
        AddNewProductRequest request = new AddNewProductRequest("ValidName",
                "Valid Description", "Fruits");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorsIfProductNameOrDescriptionIsNotProvided() {
        AddNewProductRequest request = new AddNewProductRequest("",
                "", "Fruits");
        List<CoreError> errors = validator.validate(request);

        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "Product name");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
        assertEquals(errors.get(1).getField(), "Product description");
        assertEquals(errors.get(1).getMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorIfProductNameHasDigits() {
        AddNewProductRequest request = new AddNewProductRequest("Product1",
                "Valid description", "Fruits");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product name");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
    }

    @Test
    public void shouldReturnErrorIfProductNameHasWhiteSpaces() {
        AddNewProductRequest request = new AddNewProductRequest("Product one",
                "Valid description", "Fruits");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product name");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
    }

    @Test
    public void shouldReturnErrorIfProductDescriptionHasSpecialSimbols() {
        AddNewProductRequest request = new AddNewProductRequest("Product",
                "*** Invalid description ***", "Fruits");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product description");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters and digits!");
    }

    @Test
    public void shouldReturnErrorIfProductCategoryHasDigits() {
        AddNewProductRequest request = new AddNewProductRequest("Product",
                "Valid description", "Fruits1");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "Product category");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
    }

    @Test
    public void shouldReturnErrorIfProductCategoryHasWhiteSpaces() {
        AddNewProductRequest request = new AddNewProductRequest("Product",
                "Valid description", "Apple Fruits");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "Product category");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
    }

    @Test
    public void shouldReturnErrorIfProductCategorysDoesNotExistInDB() {
        AddNewProductRequest request = new AddNewProductRequest("Product",
                "Valid description", "NoSuchCategory");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product category");
        assertEquals(errors.get(0).getMessage(), "Does not exist!");
    }
}