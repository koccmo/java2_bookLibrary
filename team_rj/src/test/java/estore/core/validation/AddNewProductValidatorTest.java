package estore.core.validation;

import estore.core.requests.AddNewProductRequest;
import estore.database.ProductCategoryDB;
import estore.database.ProductCategoryDBImpl;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddNewProductValidatorTest {

    private ProductCategoryDB categoryDB = new ProductCategoryDBImpl();
    private ValidationRules validationRules = new ValidationRules();
    private AddNewProductValidator validator = new AddNewProductValidator(categoryDB, validationRules);

    @Test
    public void shouldNotReturnErrorIfProductNameIsProvided() {
        AddNewProductRequest request = new AddNewProductRequest("ValidName",
                "Valid Description", "1");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorIfProductDescriptionIsProvided() {
        AddNewProductRequest request = new AddNewProductRequest("ValidName",
                "Valid Description", "1");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorsIfProductNameOrDescriptionIsNotProvided() {
        AddNewProductRequest request = new AddNewProductRequest("",
                "", "1");
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
                "Valid description", "1");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product name");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
    }

    @Test
    public void shouldReturnErrorIfProductNameHasWhiteSpaces() {
        AddNewProductRequest request = new AddNewProductRequest("Product one",
                "Valid description", "1");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product name");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
    }

    @Test
    public void shouldReturnErrorIfProductDescriptionHasSpecialSymbols() {
        AddNewProductRequest request = new AddNewProductRequest("Product",
                "*** Invalid description ***", "1");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product description");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters and digits!");
    }

    @Disabled
    @Test
    public void shouldNotReturnErrorIfProductCategoryHasDigits() {
        AddNewProductRequest request = new AddNewProductRequest("Product",
                "Valid description", "1");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
//        assertEquals(errors.get(0).getField(), "Product category");
//        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
    }

//    @Test
//    public void shouldReturnErrorIfProductCategoryHasWhiteSpaces() {
//        AddNewProductRequest request = new AddNewProductRequest("Product",
//                "Valid description", "Apple Fruits");
//        List<CoreError> errors = validator.validate(request);
//        assertEquals(errors.size(), 2);
//        assertEquals(errors.get(0).getField(), "Product category");
//        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
//    }

    @Test
    public void shouldReturnErrorIfProductCategoryDoesNotExistInDB() {
        AddNewProductRequest request = new AddNewProductRequest("Product",
                "Valid description", "NoSuchCategory");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(1).getField(), "Product category");
        assertEquals(errors.get(1).getMessage(), "Does not exist!");
        assertEquals(errors.get(0).getMessage(), "Must contain only digits!");
    }
}