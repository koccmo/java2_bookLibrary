package electronic_library.core.services.validators;

import electronic_library.core.requests.book.FindBooksRequest;
import electronic_library.core.requests.Ordering;
import electronic_library.core.requests.Paging;
import electronic_library.core.responses.CoreError;
import electronic_library.core.services.book.validators.FindBooksRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class FindBooksRequestValidatorTest {

    private final FindBooksRequestValidator validator = new FindBooksRequestValidator();

    @Test
    public void shouldNotReturnErrorsWhenTitleIsProvided() {
        FindBooksRequest request = new FindBooksRequest("aaa", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenAuthorIsProvided() {
        FindBooksRequest request = new FindBooksRequest(null, "aaa");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldNotReturnErrorsWhenTitleAndAuthorIsProvided() {
        FindBooksRequest request = new FindBooksRequest("aaa", "aaa");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenSearchFieldsAreEmpty() {
        FindBooksRequest request = new FindBooksRequest(null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getErrorField(), "bookTitle");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
        assertEquals(errors.get(1).getErrorField(), "bookAuthor");
        assertEquals(errors.get(1).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionAreEmpty() {
        Ordering ordering = new Ordering("title", null);
        FindBooksRequest request = new FindBooksRequest("aaa", "aaa", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "orderDirection");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByAreEmpty() {
        Ordering ordering = new Ordering(null, "ASC");
        FindBooksRequest request = new FindBooksRequest("aaa", "aaa", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "orderBy");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenOrderByContainNotValidValue() {
        Ordering ordering = new Ordering("notValidValue", "ASC");
        FindBooksRequest request = new FindBooksRequest("aaa", "aaa", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "orderBy");
        assertEquals(errors.get(0).getErrorMessage(), "Must contain 'title' or 'author' only!");
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionContainNotValidValue() {
        Ordering ordering = new Ordering("title", "notValidValue");
        FindBooksRequest request = new FindBooksRequest("aaa", "aaa", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "orderDirection");
        assertEquals(errors.get(0).getErrorMessage(), "Must contain 'ASC' or 'DESC' only!");
    }

    @Test
    public void shouldReturnErrorWhenPageNumberContainNotValidValue() {
        Paging paging = new Paging(0, 1);
        FindBooksRequest request = new FindBooksRequest("aaa", "aaa", paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "pageNumber");
        assertEquals(errors.get(0).getErrorMessage(), "Must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeContainNotValidValue() {
        Paging paging = new Paging(1, 0);
        FindBooksRequest request = new FindBooksRequest("aaa", "aaa", paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "pageSize");
        assertEquals(errors.get(0).getErrorMessage(), "Must be greater then 0!");
    }

    @Test
    public void shouldReturnErrorWhenPageNumberAreEmpty() {
        Paging paging = new Paging(null, 1);
        FindBooksRequest request = new FindBooksRequest("aaa", "aaa", paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "pageNumber");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }

    @Test
    public void shouldReturnErrorWhenPageSizeAreEmpty() {
        Paging paging = new Paging(1, null);
        FindBooksRequest request = new FindBooksRequest("aaa", "aaa", paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getErrorField(), "pageSize");
        assertEquals(errors.get(0).getErrorMessage(), "Must not be empty!");
    }
}
