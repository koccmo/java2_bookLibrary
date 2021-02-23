package book_library.core.validators.Book;

import book_library.core.requests.Ordering;
import book_library.core.requests.Paging;
import book_library.core.requests.Book.SearchBooksRequest;
import book_library.core.responses.CoreError;
import book_library.core.validators.Book.SearchBooksRequestValidator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchBooksRequestValidatorTest {
    private SearchBooksRequestValidator validator = new SearchBooksRequestValidator();

    @Test
    public void successWithTitle() {
        SearchBooksRequest request = new SearchBooksRequest( "Title", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void successWithAuthor() {
        SearchBooksRequest request = new SearchBooksRequest( null, "Author");
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void successWithTitleAndAuthor() {
        SearchBooksRequest request = new SearchBooksRequest( "Title", "Author");
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnErrorWhenTitleAndAuthorIsEmpty() {
        SearchBooksRequest request = new SearchBooksRequest( "", "");
        List<CoreError> errors = validator.validate(request);
        assertEquals( 2, errors.size());
        assertEquals("title", errors.get(0).getField());
        assertEquals( "Must not be empty", errors.get(0).getMessage());
        assertEquals("author", errors.get(1).getField());
        assertEquals("Must not be empty", errors.get(1).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenTitleAndAuthorIsNull() {
        SearchBooksRequest request = new SearchBooksRequest( null, null);
        List<CoreError> errors = validator.validate(request);
        assertEquals( 2, errors.size());
        assertEquals("title", errors.get(0).getField());
        assertEquals( "Must not be empty", errors.get(0).getMessage());
        assertEquals("author", errors.get(1).getField());
        assertEquals("Must not be empty", errors.get(1).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenTitleIsEmptyAndAuthorIsNull() {
        SearchBooksRequest request = new SearchBooksRequest( "", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals( 2, errors.size());
        assertEquals("title", errors.get(0).getField());
        assertEquals( "Must not be empty", errors.get(0).getMessage());
        assertEquals("author", errors.get(1).getField());
        assertEquals("Must not be empty", errors.get(1).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenTitleIsNullAndAuthorIsEmpty() {
        SearchBooksRequest request = new SearchBooksRequest( null, "");
        List<CoreError> errors = validator.validate(request);
        assertEquals( 2, errors.size());
        assertEquals("title", errors.get(0).getField());
        assertEquals( "Must not be empty", errors.get(0).getMessage());
        assertEquals("author", errors.get(1).getField());
        assertEquals("Must not be empty", errors.get(1).getMessage());
    }

    @Test
    public void successWithOrdering() {
        Ordering ordering = new Ordering("title", "ASCENDING");
        SearchBooksRequest request = new SearchBooksRequest("title", "author", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnErrorWhenOrderByIsNullAndOrderDirectionContainValidValue() {
        Ordering ordering = new Ordering(null,"ASCENDING");
        SearchBooksRequest request = new SearchBooksRequest( "title", "author", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals( 1, errors.size());
        assertEquals("orderBy", errors.get(0).getField());
        assertEquals( "Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenOrderByIsNullAndOrderDirectionContainNotValidValue() {
        Ordering ordering = new Ordering(null,"notValidValue");
        SearchBooksRequest request = new SearchBooksRequest( "title", "author", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals( 2, errors.size());
        assertEquals("orderDirection", errors.get(0).getField());
        assertEquals( "Must contain 'ASCENDING' or 'DESCENDING' only!", errors.get(0).getMessage());
        assertEquals("orderBy", errors.get(1).getField());
        assertEquals( "Must not be empty!", errors.get(1).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenOrderByContainValidValueAndOrderDirectionIsNull() {
        Ordering ordering = new Ordering("title",null);
        SearchBooksRequest request = new SearchBooksRequest( "title", "author", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals( 1, errors.size());
        assertEquals("orderDirection", errors.get(0).getField());
        assertEquals( "Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenOrderByContainsNotValidValueAndOrderDirectionIsNull() {
        Ordering ordering = new Ordering("notValidValue",null);
        SearchBooksRequest request = new SearchBooksRequest( "title", "author", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals( 2, errors.size());
        assertEquals("orderBy", errors.get(0).getField());
        assertEquals( "Must contain 'author' or 'title' only!", errors.get(0).getMessage());
        assertEquals("orderDirection", errors.get(1).getField());
        assertEquals( "Must not be empty!", errors.get(1).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenOrderDirectionContainNotValidValue() {
        Ordering ordering = new Ordering("title", "noValidValue");
        SearchBooksRequest request = new SearchBooksRequest("title", "author", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("orderDirection", errors.get(0).getField());
        assertEquals("Must contain 'ASCENDING' or 'DESCENDING' only!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenOrderByContainNotValidValue() {
        Ordering ordering = new Ordering("noValidValue","ASCENDING");
        SearchBooksRequest request = new SearchBooksRequest( "title", "author", ordering);
        List<CoreError> errors = validator.validate(request);
        assertEquals( 1, errors.size());
        assertEquals("orderBy", errors.get(0).getField());
        assertEquals( "Must contain 'author' or 'title' only!", errors.get(0).getMessage());
    }

    @Test
    public void successWithPaging() {
        Paging paging = new Paging(1, 1);
        SearchBooksRequest request = new SearchBooksRequest("title", "author", paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnErrorWhenPageNumberContainNotValidValueAndPageSizeContainValidValue() {
        Paging paging = new Paging(0, 1);
        SearchBooksRequest request = new SearchBooksRequest("title", "author", paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("pageNumber", errors.get(0).getField());
        assertEquals( "Must be greater then 0!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenPageNumberContainValidValueAndPageSizeContainNotValidValue() {
        Paging paging = new Paging(1, 0);
        SearchBooksRequest request = new SearchBooksRequest("title", "author", paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("pageSize", errors.get(0).getField());
        assertEquals( "Must be greater then 0!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenPageNumberIsNullAndPageSizeContainValidValue() {
        Paging paging = new Paging(null, 1);
        SearchBooksRequest request = new SearchBooksRequest("title", "author", paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("pageNumber", errors.get(0).getField());
        assertEquals( "Must not be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorWhenPageNumberContainValidValueAndPageSizeIsNull() {
        Paging paging = new Paging(1, null);
        SearchBooksRequest request = new SearchBooksRequest("title", "author", paging);
        List<CoreError> errors = validator.validate(request);
        assertEquals(1, errors.size());
        assertEquals("pageSize", errors.get(0).getField());
        assertEquals( "Must not be empty!", errors.get(0).getMessage());
    }


}