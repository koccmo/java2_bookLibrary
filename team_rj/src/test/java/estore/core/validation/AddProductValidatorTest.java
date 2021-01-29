package estore.core.validation;

import estore.core.requests.AddProductRequest;
import estore.database.ProductCategoryRepository;
import estore.database.inmemoryrepo.ProductCategoryRepositoryImpl;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddProductValidatorTest {

    private ProductCategoryRepository categoryDB = new ProductCategoryRepositoryImpl();
    private ValidationRules validationRules = new ValidationRules();
    private AddProductValidator validator = new AddProductValidator(categoryDB, validationRules);

    @Test
    public void shouldNotReturnErrorIfProductNameIsProvided() {
        AddProductRequest request = new AddProductRequest("ValidName",
                "Valid Description", "1");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorIfProductDescriptionIsProvided() {
        AddProductRequest request = new AddProductRequest("ValidName",
                "Valid Description", "1");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorsIfProductNameOrDescriptionIsNotProvided() {
        AddProductRequest request = new AddProductRequest("",
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
        AddProductRequest request = new AddProductRequest("Product1",
                "Valid description", "1");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product name");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
    }

    @Test
    public void shouldReturnErrorIfProductNameHasWhiteSpaces() {
        AddProductRequest request = new AddProductRequest("Product one",
                "Valid description", "1");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product name");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
    }

    @Test
    public void shouldReturnErrorIfProductDescriptionHasSpecialSymbols() {
        AddProductRequest request = new AddProductRequest("Product",
                "*** Invalid description ***", "1");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product description");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters and digits!");
    }

    @Disabled
    @Test
    public void shouldNotReturnErrorIfProductCategoryHasDigits() {
        AddProductRequest request = new AddProductRequest("Product",
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
        AddProductRequest request = new AddProductRequest("Product",
                "Valid description", "NoSuchCategory");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(1).getField(), "Product category");
        assertEquals(errors.get(1).getMessage(), "Does not exist!");
        assertEquals(errors.get(0).getMessage(), "Must contain only digits!");
    }
}