package bookLibrary.core.service.validators;

import bookLibrary.core.request.Ordering;
import bookLibrary.core.request.Paging;
import bookLibrary.core.request.SearchBooksRequest;
import bookLibrary.core.response.CoreError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchBooksValidatorTest {
    @Mock private SearchBooksRequestFieldValidator fieldValidator;
    @Mock private OrderingValidator orderingValidator;

    @Mock private PagingValidator pagingValidator;
    @InjectMocks private SearchBooksValidator validator;

    @Test
    public void fieldTitleEmpty() {
        SearchBooksRequest request = new SearchBooksRequest("James", "");
        when(fieldValidator.validate(request)).thenReturn(List.of());
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void fieldAuthorEmpty() {
        SearchBooksRequest request = new SearchBooksRequest("", "Garden");
        when(fieldValidator.validate(request)).thenReturn(List.of());
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void fieldTitleAndAuthorEmpty() {
        SearchBooksRequest request = new SearchBooksRequest("", "");
        when(fieldValidator.validate(request)).thenReturn(List.of(
                new CoreError("Author", "Field Author Must be Fill UP!"),
                new CoreError("Title", "Field Title Must Be Fill UP!")));
        List<CoreError> errors = validator.validate(request);
        assertFalse(errors.isEmpty());
        assertEquals("Author", errors.get(0).getField());
        assertEquals("Field Author Must be Fill UP!", errors.get(0).getDescription());
        assertEquals("Title", errors.get(1).getField());
        assertEquals("Field Title Must Be Fill UP!", errors.get(1).getDescription());
    }

    @Test
    public void orderByAuthorButAuthorIsEmpty() {
        Ordering ordering = new Ordering("Author", "ASCENDING");
        SearchBooksRequest request = new SearchBooksRequest("", "Garden", ordering);
        when(orderingValidator.validate(ordering)).thenReturn(List.of());
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void orderByTitleButTitleEmpty() {
        Ordering ordering = new Ordering("Title", "ASCENDING");
        SearchBooksRequest request = new SearchBooksRequest("James", "", ordering);
        when(orderingValidator.validate(ordering)).thenReturn(List.of());
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }
    @Test
    public void shouldReturnErrorWhenOrderByNotValidValue() {
        Ordering ordering = new Ordering("Year", "ASCENDING");
        SearchBooksRequest request = new SearchBooksRequest("James", "Garden", ordering);
        CoreError error = new CoreError("OrderBy", "Must be equal Author or Title!");
        when(orderingValidator.validate(ordering)).thenReturn(List.of(error));
        List<CoreError> errors = validator.validate(request);
        CoreError expectedError = new CoreError("OrderBy", "Must be equal Author or Title!");
        assertFalse(errors.isEmpty());
        assertEquals(expectedError, errors.get(0));
    }
    @Test
    public void orderDirectionWrongSpecification() {
        Ordering ordering = new Ordering("Title", "Entry");
        SearchBooksRequest request = new SearchBooksRequest("James", "Garden", ordering);
        CoreError error= new CoreError("OrderDirection", "Must be choosing one of Direction!");
        when(orderingValidator.validate(ordering)).thenReturn(List.of(error));
        List<CoreError> errors = validator.validate(request);
        CoreError expectedError = new CoreError("OrderDirection", "Must be choosing one of Direction!");
        assertFalse(errors.isEmpty());
        assertEquals(expectedError, errors.get(0));
    }
    @Test
    public void orderByEmptyOrderDirectionCorrect() {
        Ordering ordering = new Ordering(null, "DESCENDING");
        SearchBooksRequest request = new SearchBooksRequest("James", "Garden", ordering);
        CoreError error = new CoreError("OrderBy", "Must be fill up!");
        when(orderingValidator.validate(ordering)).thenReturn(List.of(error));
        List<CoreError> errors = validator.validate(request);
        CoreError expectedError = new CoreError("OrderBy", "Must be fill up!");
        assertFalse(errors.isEmpty());
        assertEquals(expectedError, errors.get(0));
    }

    @Test
    public void orderDirectionEmptyOrderByCorrect() {
        Ordering ordering = new Ordering("Title", null);
        SearchBooksRequest request = new SearchBooksRequest("James", "Garden", ordering);
        CoreError error = new CoreError("OrderDirection", "Must be fill up!");
        when(orderingValidator.validate(ordering)).thenReturn(List.of(error));
        List<CoreError> errors = validator.validate(request);
        CoreError expectedError = new CoreError("OrderDirection", "Must be fill up!");
        assertFalse(errors.isEmpty());
        assertEquals(expectedError, errors.get(0));
    }
    @Test
    public void orderByAndOrderDirectionNotValidValue() {
        Ordering ordering = new Ordering("Entry", "Down");
        SearchBooksRequest request = new SearchBooksRequest("James", "Garden", ordering);
        CoreError orderByError = new CoreError("OrderBy", "Must be equal Author or Title!");
        CoreError orderDirectionError = new CoreError("OrderDirection", "Must be choosing one of Direction!");
        when(orderingValidator.validate(ordering)).thenReturn(
                List.of(orderByError, orderDirectionError));

        List<CoreError> errors = validator.validate(request);
        CoreError orderByExpectedError = new CoreError("OrderBy", "Must be equal Author or Title!");
        CoreError orderDirectionExpectedError = new CoreError("OrderDirection", "Must be choosing one of Direction!");

        assertFalse(errors.isEmpty());
        assertEquals(orderByExpectedError, errors.get(0));
        assertEquals(orderDirectionExpectedError, errors.get(1));
    }

    @Test
    public void shouldNotInvokeWhenOrderValidationWhenOrderingNull() {
        SearchBooksRequest request = new SearchBooksRequest("Author", "Title");
        validator.validate(request);
        verifyNoMoreInteractions(orderingValidator);
    }

    @Test
    public void pageNumberIsNullPageSizeIsOne() {
        Paging paging = new Paging(null, 1);
        SearchBooksRequest request = new SearchBooksRequest("a", "", null, paging);
        CoreError error = new CoreError("PageNumber", "Must be fill UP!");
        when(pagingValidator.validate(paging)).thenReturn(List.of(error));
        List<CoreError> errors = validator.validate(request);
        assertEquals("PageNumber", errors.get(0).getField());
        assertEquals("Must be fill UP!", errors.get(0).getDescription());
        assertEquals(1, errors.size());
    }

    @Test
    public void pageSizeNullPageNumberOne() {
        Paging paging = new Paging(1, null);
        SearchBooksRequest request = new SearchBooksRequest("A", "", null, paging);
        CoreError error = new CoreError("PageSize", "Must be fill UP!");
        when(pagingValidator.validate(paging)).thenReturn(List.of(error));
        List<CoreError> errors = validator.validate(request);
        assertEquals("PageSize", errors.get(0).getField());
        assertEquals("Must be fill UP!", errors.get(0).getDescription());
        assertEquals(1, errors.size());
    }

    @Test
    public void pageNumberNullPageSizeNull() {
        Paging paging = new Paging(null, null);
        when(pagingValidator.validate(paging)).thenReturn(List.of());
        SearchBooksRequest request = new SearchBooksRequest("A", "", null, paging);
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    public void pageNumberValueCorrectPageSizeValueZero() {
        Paging paging = new Paging(1, 0);
        SearchBooksRequest request = new SearchBooksRequest("A", "", null, paging);
        CoreError error = new CoreError("PageSize", "Must be over 0!");
        when(pagingValidator.validate(paging)).thenReturn(List.of(error));
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("PageSize", errors.get(0).getField());
        assertEquals("Must be over 0!", errors.get(0).getDescription());
    }

    @Test
    public void pageSizeValueCorrectPageNumberValueZero() {
        Paging paging = new Paging(0, 1);
        CoreError error = new CoreError("PageNumber", "Must be over 0!");
        when(pagingValidator.validate(paging)).thenReturn(List.of(error));

        SearchBooksRequest request = new SearchBooksRequest("A", "", null, paging);
        List<CoreError> errors = validator.validate(request);

        assertEquals(1, errors.size());
        assertEquals("PageNumber", errors.get(0).getField());
        assertEquals("Must be over 0!", errors.get(0).getDescription());
    }

    @Test
    public void unCorrectPageNumberAndPageSize() {
        Paging paging = new Paging(0, 0);
        SearchBooksRequest request = new SearchBooksRequest("A", "", null, paging);
        CoreError pageNumberError = new CoreError("PageNumber", "Must be over 0!");
        CoreError pageSizeError = new CoreError("PageSize", "Must be over 0!");
        when(pagingValidator.validate(paging)).thenReturn(List.of(pageNumberError, pageSizeError));
        List<CoreError> errors = validator.validate(request);
        assertEquals(2, errors.size());
        assertEquals("PageNumber", errors.get(0).getField());
        assertEquals("Must be over 0!", errors.get(0).getDescription());
        assertEquals("PageSize", errors.get(1).getField());
        assertEquals("Must be over 0!", errors.get(1).getDescription());
    }

}