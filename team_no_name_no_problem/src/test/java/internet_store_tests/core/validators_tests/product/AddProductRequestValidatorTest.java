package internet_store_tests.core.validators_tests.product;

import internet_store.core.domain.Product;
import internet_store.core.requests.product.AddProductRequest;
import internet_store.core.response.CoreError;
import internet_store.core.services.product.validators.AddProductRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AddProductRequestValidatorTest {

    AddProductRequestValidator addProductRequestValidator = new AddProductRequestValidator();

    @Test
    public void testInvalidInputTitle(){
        CoreError expectedError = new CoreError("title", "Not valid input for title");

        Product product = new Product("", "Description", 5);
        AddProductRequest addProductRequest = new AddProductRequest(product);
        List<CoreError> errors = addProductRequestValidator.validate(addProductRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testInvalidInputDescription(){
        CoreError expectedError = new CoreError("description", "Not valid input for description");

        Product product = new Product("Title", null, 5);
        AddProductRequest addProductRequest = new AddProductRequest(product);
        List<CoreError> errors = addProductRequestValidator.validate(addProductRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testInvalidInputPrice(){
        CoreError expectedError = new CoreError("price", "Not valid input for price");

        Product product = new Product("Title", "Description", -2);
        AddProductRequest addProductRequest = new AddProductRequest(product);
        List<CoreError> errors = addProductRequestValidator.validate(addProductRequest);

        assertTrue(errors.size() == 1);
        assertTrue(errors.contains(expectedError));
    }

    @Test
    public void testValidInput(){

        Product product = new Product("Title", "Description", 5);
        AddProductRequest addProductRequest = new AddProductRequest(product);
        List<CoreError> errors = addProductRequestValidator.validate(addProductRequest);

        assertTrue(errors.isEmpty());
    }

}