package estore.core.validation;

import estore.core.requests.AddProductCategoryRequest;
import estore.database.ProductCategoryRepository;
import estore.database.inmemoryrepo.ProductCategoryRepositoryImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddProductCategoryValidatorTest {

    private ProductCategoryRepository categoryDB = new ProductCategoryRepositoryImpl();
    private ValidationRules validationRules = new ValidationRules();
    private AddProductCategoryValidator validator = new AddProductCategoryValidator(categoryDB, validationRules);

    @Test
    public void shouldNotReturnErrorIfProductCategoryIsProvided() {
        AddProductCategoryRequest request = new AddProductCategoryRequest("Category");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorIfProductCategoryHasDigits() {
        AddProductCategoryRequest request = new AddProductCategoryRequest("Category1");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product category");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
    }

    @Test
    public void shouldReturnErrorIfProductCategoryHasSpecualSymbols() {
        AddProductCategoryRequest request = new AddProductCategoryRequest("Category one$");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Product category");
        assertEquals(errors.get(0).getMessage(), "Must contain only english letters!");
    }
}