package internet_store.application.core.services.product.validators;

import internet_store.application.core.requests.product.FindProductsRequest;
import internet_store.application.core.requests.product.Ordering;
import internet_store.application.core.requests.product.Paging;
import internet_store.application.core.responses.CoreError;
import org.junit.Before;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;

public class FindProductsRequestValidatorTest {

    private FindProductsRequestValidator validator;
    private Ordering ordering;
    private Paging paging;

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
    public void shouldNotReturnErrorsWhenNameAndDescriptionIsProvided() {
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
        ordering = new Ordering("Name", "Descending");
        FindProductsRequest request = new FindProductsRequest("ProductName"
                , "ProductDescription", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorIfOnlyOneOrderingFieldIsFilled() {
        ordering = new Ordering("Name", "");
        FindProductsRequest request = new FindProductsRequest("ProductName"
                , "ProductDescription", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(2, errors.size());
        assertEquals("Ordering Fields", errors.get(0).getField());
        assertEquals("Both must be empty or filled!", errors.get(0).getMessage());
        assertEquals("Direction", errors.get(1).getField());
        assertEquals("Must be Ascending or Descending.", errors.get(1).getMessage());
    }

    @Test
    public void shouldReturnErrorIfOrderingNameIsWrong() {
        ordering = new Ordering("price", "Ascending");
        FindProductsRequest request = new FindProductsRequest("ProductName"
                , "ProductDescription", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Ordering by", errors.get(0).getField());
        assertEquals("Must be Name or Description.", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorIfOrderingDirectionIsWrong() {
        ordering = new Ordering("Name", "alphabet");
        FindProductsRequest request = new FindProductsRequest("ProductName"
                , "ProductDescription", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Direction", errors.get(0).getField());
        assertEquals("Must be Ascending or Descending.", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenOnlyPageNumberProvided() {
        paging = new Paging(null, 2);
        FindProductsRequest request = new FindProductsRequest("ProductName"
                , "ProductDescription"
                , paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Page number and page size", errors.get(0).getField());
        assertEquals("Both must be empty or filled.", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenOnlyPageSizeProvided() {
        paging = new Paging(2, null);
        FindProductsRequest request = new FindProductsRequest("ProductName"
                , "ProductDescription"
                , paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Page number and page size", errors.get(0).getField());
        assertEquals("Both must be empty or filled.", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnNoErrorWhenBothPagingFieldsNotProvided() {
        paging = new Paging(null, null);
        FindProductsRequest request = new FindProductsRequest("ProductName"
                , "ProductDescription"
                , paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnNoErrorWhenBothPagingFieldsProvided() {
        paging = new Paging(10, 3);
        FindProductsRequest request = new FindProductsRequest("ProductName"
                , "ProductDescription"
                , paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnErrorWhenPageSizeIsZero() {
        paging = new Paging(2, 0);
        FindProductsRequest request = new FindProductsRequest("ProductName"
                , "ProductDescription"
                , paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Page size", errors.get(0).getField());
        assertEquals("Must be bigger than zero.", errors.get(0).getMessage());
    }

    @Test
    public void ShouldReturnErrorWhenPageSizeIsLessThanZero() {
        paging = new Paging(2, -4);
        FindProductsRequest request = new FindProductsRequest("ProductName"
                , "ProductDescription"
                , paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Page size", errors.get(0).getField());
        assertEquals("Must be bigger than zero.", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenPageNumberIsZero() {
        paging = new Paging(0, 9);
        FindProductsRequest request = new FindProductsRequest("ProductName"
                , "ProductDescription"
                , paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Page number", errors.get(0).getField());
        assertEquals("Must be bigger than zero.", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenPageNumberIsLessThanZero() {
        paging = new Paging(-3, 3);
        FindProductsRequest request = new FindProductsRequest("ProductName"
                , "ProductDescription"
                , paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("Page number", errors.get(0).getField());
        assertEquals("Must be bigger than zero.", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnTwoErrorsWhenPageNumberAndSizeIsZero() {
        paging = new Paging(0, 0);
        FindProductsRequest request = new FindProductsRequest("ProductName"
                , "ProductDescription"
                , paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(2, errors.size());
        assertEquals("Page size", errors.get(0).getField());
        assertEquals("Must be bigger than zero.", errors.get(0).getMessage());
        assertEquals("Page number", errors.get(1).getField());
        assertEquals("Must be bigger than zero.", errors.get(1).getMessage());
    }

}

