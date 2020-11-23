package internet_store.lesson_3.core.services.validators;

import internet_store.lesson_3.core.requests.FindProductsRequest;
import internet_store.lesson_3.core.responses.CoreError;
import internet_store.lesson_3.core.services.validators.FindProductsRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FindProductsRequestValidatorTest {

    private final FindProductsRequestValidator validator = new FindProductsRequestValidator();

    @Test
    public void shouldNotReturnErrorsWhenNameIsProvided() {
        FindProductsRequest request = new FindProductsRequest("Name", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenDescriptionIsProvided() {
        FindProductsRequest request = new FindProductsRequest(null, "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenTitleAndAuthorIsProvided() {
        FindProductsRequest request = new FindProductsRequest("Name", "Description");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenSearchFieldsAreEmpty() {
        FindProductsRequest request = new FindProductsRequest(null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(), "Must not be empty!");
        assertEquals(errors.get(1).getField(), "Description");
        assertEquals(errors.get(1).getMessage(), "Must not be empty!");
    }

}