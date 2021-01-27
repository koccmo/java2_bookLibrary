package estore.core.validation;

import estore.core.requests.AddNewProductCategoryRequest;
import estore.database.ProductCategoryRepository;
import estore.database.inmemoryrepo.ProductCategoryRepositoryImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddNewProductCategoryValidatorTest {

    private ProductCategoryRepository categoryDB = new ProductCategoryRepositoryImpl();
    private ValidationRules validationRules = new ValidationRules();
    private AddNewProductCategoryValidator validator = new AddNewProductCategoryValidator(categoryDB, validationRules);

    @Test
    public void shouldNotReturnErrorIfProductCategoryIsProvided() {
        AddNewProductCategoryRequest request = new AddNewProductCategoryRequest("Category");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorIfProductCategoryHasDigits() {
        AddNewProductCategoryRequest request = new AddNewProductCategoryRequest("Category1");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product category");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
    }

    @Test
    public void shouldReturnErrorIfProductCategoryHasSpecualSymbols() {
        AddNewProductCategoryRequest request = new AddNewProductCategoryRequest("Category one$");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product category");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
    }
}