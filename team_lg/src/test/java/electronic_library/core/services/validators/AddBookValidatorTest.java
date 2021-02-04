package electronic_library.core.services.validators;

import electronic_library.core.requests.book.AddBookRequest;
import electronic_library.core.responses.CoreError;
import electronic_library.core.services.book.validators.AddBookValidator;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AddBookValidatorTest {
    private AddBookValidator validator;

    @Before
    public void setUp() {
        validator = new AddBookValidator();
    }

    @Test
    public void shouldNotValidBookTitleWhenItIsNull() {
        List<CoreError> result = validator.validate(new AddBookRequest(null, "aaa", new BigDecimal("10.00"), 2010));
        assertEquals(1, result.size());
        assertEquals("Book Title", result.get(0).getErrorField());
    }

    @Test
    public void shouldNotValidBookTitleWhenItIsEmpty() {
        List<CoreError> result = validator.validate(new AddBookRequest("", "aaa", new BigDecimal("10.00"), 2010));
        assertEquals(1, result.size());
        assertEquals("Book Title", result.get(0).getErrorField());
    }

    @Test
    public void shouldNotValidBookAuthorWhenItIsNull() {
        List<CoreError> result = validator.validate(new AddBookRequest("aaa", null, new BigDecimal("10.00"), 2010));
        assertEquals(1, result.size());
        assertEquals("Book Author", result.get(0).getErrorField());
    }

    @Test
    public void shouldNotValidBookAuthorWhenItIsEmpty() {
        List<CoreError> result = validator.validate(new AddBookRequest("aaa", null, new BigDecimal("10.00"), 2010));
        assertEquals(1, result.size());
        assertEquals("Book Author", result.get(0).getErrorField());
    }

    @Test
    public void shouldNotValidBookPriceWhenItIsNull() {
        List<CoreError> result = validator.validate(new AddBookRequest("aaa", "aaa", null, 2010));
        assertEquals(1, result.size());
        assertEquals("Book Price", result.get(0).getErrorField());
    }

    @Test
    public void shouldNotValidBookPriceWhenItIsZero() {
        List<CoreError> result = validator.validate(new AddBookRequest("aaa", "aaa", new BigDecimal("0.00"), 2010));
        assertEquals(1, result.size());
        assertEquals("Book Price", result.get(0).getErrorField());
    }

    @Test
    public void shouldNotValidYearOfBookIssueWhenItIsNegative() {
        List<CoreError> result = validator.validate(new AddBookRequest("aaa", "aaa", new BigDecimal("10.00"), -1));
        assertEquals(1, result.size());
        assertEquals("Year Of Book Issue", result.get(0).getErrorField());
    }

    @Test
    public void shouldNotValidYearOfBookIssueWhenItIsZero() {
        List<CoreError> result = validator.validate(new AddBookRequest("aaa", "aaa", new BigDecimal("10.00"), 0));
        assertEquals(1, result.size());
        assertEquals("Year Of Book Issue", result.get(0).getErrorField());
    }
}
