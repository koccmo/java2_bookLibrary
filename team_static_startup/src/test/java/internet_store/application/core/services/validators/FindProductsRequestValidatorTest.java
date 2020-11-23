package internet_store.application.core.services.validators;

import internet_store.application.core.requests.FindProductsRequest;
import internet_store.application.core.responses.CoreError;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class FindProductsRequestValidatorTest {

    private FindProductsRequestValidator validator;

    @Before
    public void setUp() {
        validator = new FindProductsRequestValidator();
    }

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

    @Test
    public void shouldNotReturnErrorsOrderingFieldIsFilled() {
        FindProductsRequest request = new FindProductsRequest("ProductName", "ProductDescription",
                "Name", "Descending");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorIfOnlyOneOrderingFieldIsFilled() {
        FindProductsRequest request = new FindProductsRequest("ProductName", "ProductDescription",
                "Name", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Ordering Fields", errors.get(0).getField());
        assertEquals("Both must be empty or filled!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorIfOrderingNameIsWrong() {
        FindProductsRequest request = new FindProductsRequest("ProductName", "ProductDescription",
                "price", "alphabet");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Ordering by", errors.get(0).getField());
        assertEquals("Must be Name or Description.", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorIfOrderingDirectionIsWrong() {
        FindProductsRequest request = new FindProductsRequest("ProductName", "ProductDescription",
                "Name", "alphabet");
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Direction", errors.get(0).getField());
        assertEquals("Must be Ascending or Descending.", errors.get(0).getMessage());
    }


}