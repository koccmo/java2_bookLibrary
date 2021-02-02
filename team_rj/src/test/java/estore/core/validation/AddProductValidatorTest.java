package estore.core.validation;

import estore.core.requests.AddProductRequest;
import estore.database.ProductCategoryRepository;
import estore.database.inmemoryrepo.ProductCategoryRepositoryImpl;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class AddProductValidatorTest {

    private ProductCategoryRepository categoryDB = new ProductCategoryRepositoryImpl();
    private ValidationRules validationRules = new ValidationRules();
    private AddProductValidator validator = new AddProductValidator(categoryDB, validationRules);

    @Test
    public void shouldNotReturnErrorIfProductNameIsProvided() {
        AddProductRequest request = new AddProductRequest("ValidName",
                "Valid Description", "Category");
        List<CoreError> errors = validator.validate(request);

        // Fails to validate category existence
        assertEquals(errors.size(), 1);
        assertThat("Product category").isEqualTo(errors.get(0).getField());
        assertThat("Does not exist!").isEqualTo(errors.get(0).getMessage());
    }

    @Test
    public void shouldNotReturnErrorIfProductDescriptionIsProvided() {
        AddProductRequest request = new AddProductRequest("ValidName",
                "Valid Description", "Category");
        List<CoreError> errors = validator.validate(request);

        // Fails to validate category existence
        assertEquals(errors.size(), 1);
        assertThat("Product category").isEqualTo(errors.get(0).getField());
        assertThat("Does not exist!").isEqualTo(errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorsIfProductNameOrDescriptionIsNotProvided() {
        AddProductRequest request = new AddProductRequest("",
                "", "Category");
        List<CoreError> errors = validator.validate(request);

        // Fails to validate category existence
        assertEquals(errors.size(), 3);
        assertEquals(errors.get(0).getField(), "Product name");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
        assertEquals(errors.get(1).getField(), "Product description");
        assertEquals(errors.get(1).getMessage(), "Must not be empty!");
        assertThat("Product category").isEqualTo(errors.get(2).getField());
        assertThat("Does not exist!").isEqualTo(errors.get(2).getMessage());
    }

    @Test
    public void shouldReturnErrorIfProductNameHasDigits() {
        AddProductRequest request = new AddProductRequest("Product1",
                "Valid description", "Category");
        List<CoreError> errors = validator.validate(request);

        // Fails to validate category existence
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "Product name");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
        assertThat("Product category").isEqualTo(errors.get(1).getField());
        assertThat("Does not exist!").isEqualTo(errors.get(1).getMessage());
    }

    @Test
    public void shouldReturnErrorIfProductNameHasWhiteSpaces() {
        AddProductRequest request = new AddProductRequest("Product one",
                "Valid description", "Category");
        List<CoreError> errors = validator.validate(request);

        // Fails to validate category existence
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "Product name");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
        assertThat("Product category").isEqualTo(errors.get(1).getField());
        assertThat("Does not exist!").isEqualTo(errors.get(1).getMessage());
    }

    @Test
    public void shouldReturnErrorIfProductDescriptionHasSpecialSymbols() {
        AddProductRequest request = new AddProductRequest("Product",
                "*** Invalid description ***", "Category");
        List<CoreError> errors = validator.validate(request);

        // Fails to validate category existence
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "Product description");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters and digits!");
        assertThat("Product category").isEqualTo(errors.get(1).getField());
        assertThat("Does not exist!").isEqualTo(errors.get(1).getMessage());
    }

    @Disabled
    @Test
    public void shouldReturnErrorIfProductCategoryHasDigits() {
        AddProductRequest request = new AddProductRequest("Product",
                "Valid description", "Category_1");
        List<CoreError> errors = validator.validate(request);

        // Fails to validate category existence
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "Product category");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
        assertThat("Product category").isEqualTo(errors.get(1).getField());
        assertThat("Does not exist!").isEqualTo(errors.get(1).getMessage());
    }

//    @Test
//    public void shouldReturnErrorIfProductCategoryHasWhiteSpaces() {
//        AddProductRequest request = new AddProductRequest("Product",
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
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product category");
        assertEquals(errors.get(0).getMessage(), "Does not exist!");
    }
}