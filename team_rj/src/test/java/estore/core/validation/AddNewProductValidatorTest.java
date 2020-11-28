package estore.core.validation;

import estore.core.requests.AddNewProductRequest;
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
}