package bookLibrary.core.service;

import bookLibrary.core.domain.Book;
import bookLibrary.core.dataBase.DataBase;
import bookLibrary.core.request.Ordering;
import bookLibrary.core.request.Paging;
import bookLibrary.core.request.SearchBooksRequest;
import bookLibrary.core.response.CoreError;
import bookLibrary.core.response.SearchBooksResponse;
import bookLibrary.core.service.validators.SearchBooksValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchBooksServiceTest {
    @Mock private SearchBooksValidator validator;
    @Mock private DataBase dataBase;
    @InjectMocks private SearchBooksService service;

    @Test
    public void shouldSearchByAuthor() {
        SearchBooksRequest request = new SearchBooksRequest("Author", "", null, null);
        when(validator.validate(request)).thenReturn(List.of());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Author", "Title"));
        when(dataBase.findByAuthor(request.getAuthor())).thenReturn(books);
        SearchBooksResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(1, response.getFoundedBookList().size());
        assertEquals("Author", response.getFoundedBookList().get(0).getAuthor());
        assertEquals("Title", response.getFoundedBookList().get(0).getTitle());
    }

    @Test
    public void shouldSearchByTitle() {
        SearchBooksRequest request = new SearchBooksRequest("", "Title");
        when(validator.validate(request)).thenReturn(List.of());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Author", "Title"));
        when(dataBase.findByTitle(request.getTitle())).thenReturn(books);
        SearchBooksResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(1, response.getFoundedBookList().size());
        assertEquals("Author", response.getFoundedBookList().get(0).getAuthor());
        assertEquals("Title", response.getFoundedBookList().get(0).getTitle());
    }

    @Test
    public void shouldSearchByAuthorAndTitle() {
        SearchBooksRequest request = new SearchBooksRequest("Author", "Title");
        when(validator.validate(request)).thenReturn(List.of());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Author", "Title"));
        when(dataBase.findByAuthorAndTitle(request.getAuthor(), request.getTitle())).thenReturn(books);
        SearchBooksResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(1, response.getFoundedBookList().size());
        assertEquals("Author", response.getFoundedBookList().get(0).getAuthor());
        assertEquals("Title", response.getFoundedBookList().get(0).getTitle());
    }

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFail() {
        SearchBooksRequest request = new SearchBooksRequest("", "");
        when(validator.validate(request))
                .thenReturn(List.of(new CoreError("Author", "Author must be Fill UP!"),
                        new CoreError("Title", "Author must be Fill UP!")));
        SearchBooksResponse response = service.execute(request);

        assertTrue(response.hasError());
        assertEquals("Author", response.getErrors().get(0).getField());
        assertEquals("Author must be Fill UP!", response.getErrors().get(0).getDescription());
        assertEquals("Title", response.getErrors().get(1).getField());
        assertEquals("Author must be Fill UP!", response.getErrors().get(1).getDescription());

        verify(validator).validate(request);
        verify(validator).validate(any());
        verifyNoInteractions(dataBase);
    }

    @Test
    public void searchByTitleWithOrderingAuthorAndAscending() {
        Ordering ordering = new Ordering("Author", "ASCENDING");
        SearchBooksRequest request = new SearchBooksRequest("", "Title", ordering, null);
        when(validator.validate(request)).thenReturn(List.of());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Alex", "Title"));
        books.add(new Book("Bob", "Title"));
        when(dataBase.findByTitle(request.getTitle())).thenReturn(books);
        SearchBooksResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(2, response.getFoundedBookList().size());
        assertEquals("Alex", response.getFoundedBookList().get(0).getAuthor());
        assertEquals("Bob", response.getFoundedBookList().get(1).getAuthor());
    }

    @Test
    public void searchByTitleOrderingAuthorAndDescending() {
        Ordering ordering = new Ordering("Author", "DESCENDING");
        SearchBooksRequest request = new SearchBooksRequest("", "Title", ordering, null);
        when(validator.validate(request)).thenReturn(List.of());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Alex", "Title"));
        books.add(new Book("Bob", "Title"));
        when(dataBase.findByTitle(request.getTitle())).thenReturn(books);
        SearchBooksResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(2, response.getFoundedBookList().size());
        assertEquals("Bob", response.getFoundedBookList().get(0).getAuthor());
        assertEquals("Alex", response.getFoundedBookList().get(1).getAuthor());
    }

    @Test
    public void searchByAuthorOrderingTitleAscending() {
        Ordering ordering = new Ordering("Title", "ASCENDING");
        SearchBooksRequest request = new SearchBooksRequest("Author", "", ordering, null);
        when(validator.validate(request)).thenReturn(List.of());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Author", "Baby"));
        books.add(new Book("Author", "Almond"));
        when(dataBase.findByAuthor(request.getAuthor())).thenReturn(books);
        SearchBooksResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(2, response.getFoundedBookList().size());
        assertEquals("Almond", response.getFoundedBookList().get(0).getTitle());
        assertEquals("Baby", response.getFoundedBookList().get(1).getTitle());
    }

    @Test
    public void searchByAuthorOrderingTitleDescending() {
        Ordering ordering = new Ordering("Title", "DESCENDING");
        SearchBooksRequest request = new SearchBooksRequest("Author", "", ordering, null);
        when(validator.validate(request)).thenReturn(List.of());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Author", "Alberto"));
        books.add(new Book("Author", "X-Man"));
        when(dataBase.findByAuthor(request.getAuthor())).thenReturn(books);
        SearchBooksResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(2, response.getFoundedBookList().size());
        assertEquals("X-Man", response.getFoundedBookList().get(0).getTitle());
        assertEquals("Alberto", response.getFoundedBookList().get(1).getTitle());
    }

    @Test
    public void shouldReturnErrorWhenOrderingValidationFailOrderByNull() {
        Ordering ordering = new Ordering(null, "ASCENDING");
        SearchBooksRequest request = new SearchBooksRequest("Author", "Title", ordering);
        when(validator.validate(request)).thenReturn(List.of(new CoreError("OrderBy", "Must be fill up!")));
        SearchBooksResponse response = service.execute(request);

        assertTrue(response.hasError());
        assertEquals("OrderBy", response.getErrors().get(0).getField());
        assertEquals("Must be fill up!", response.getErrors().get(0).getDescription());
        verify(validator).validate(request);
        verify(validator).validate(any());
        verifyNoInteractions(dataBase);
    }

    @Test
    public void shouldReturnErrorWhenOrderingValidationFailOrderDirectionNull() {
        Ordering ordering = new Ordering("Author", null);
        SearchBooksRequest request = new SearchBooksRequest("Author", "Title", ordering, null);
        when(validator.validate(request)).thenReturn(List.of(
                new CoreError("OrderDirection", "Must be fill up!")));
        SearchBooksResponse response = service.execute(request);

        assertTrue(response.hasError());
        assertEquals(1, response.getErrors().size());
        assertEquals("OrderDirection", response.getErrors().get(0).getField());
        assertEquals("Must be fill up!", response.getErrors().get(0).getDescription());
        verify(validator).validate(request);
        verify(validator).validate(any());
        verifyNoInteractions(dataBase);
    }

    @Test
    public void shouldReturnResponseWithTwoErrorsOrderByNullOrderDirectionNull() {
        Ordering ordering = new Ordering(null, null);
        SearchBooksRequest request = new SearchBooksRequest("Author", "Title", ordering, null);
        when(validator.validate(request)).thenReturn(List.of(new CoreError("OrderBy", "Must be fill up!"),
                new CoreError("OrderDirection", "Must be fill up!")));
        SearchBooksResponse response = service.execute(request);

        assertTrue(response.hasError());
        assertEquals(2, response.getErrors().size());
        assertEquals("OrderBy", response.getErrors().get(0).getField());
        assertEquals("Must be fill up!", response.getErrors().get(0).getDescription());
        assertEquals("OrderDirection", response.getErrors().get(1).getField());
        assertEquals("Must be fill up!", response.getErrors().get(1).getDescription());
        verify(validator).validate(request);
        verify(validator).validate(any());
        verifyNoInteractions(dataBase);
    }

    @Test
    public void shouldSearchByAuthorPagingFirstPage() {
        Paging paging = new Paging(1, 1);
        SearchBooksRequest request = new SearchBooksRequest("Author", "", null, paging);
        when(validator.validate(request)).thenReturn(List.of());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Author", "Hope"));
        books.add(new Book("Author", "Garden"));
        when(dataBase.findByAuthor(request.getAuthor())).thenReturn(books);
        SearchBooksResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(1, response.getFoundedBookList().size());
        assertEquals("Author", response.getFoundedBookList().get(0).getAuthor());
        assertEquals("Hope", response.getFoundedBookList().get(0).getTitle());
        verify(validator).validate(request);
        verify(validator).validate(any());
        verify(dataBase).findByAuthor(any());
    }

    @Test
    public void shouldSearchByTitlePagingSecondPage() {
        Paging paging = new Paging(2, 1);
        SearchBooksRequest request = new SearchBooksRequest("", "Title", null, paging);
        when(validator.validate(request)).thenReturn(List.of());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Big Bob", "Title"));
        books.add(new Book("Alex", "Title"));
        when(dataBase.findByTitle(request.getTitle())).thenReturn(books);
        SearchBooksResponse response = service.execute(request);

        assertFalse(response.hasError());
        assertEquals(1, response.getFoundedBookList().size());
        assertEquals("Alex", response.getFoundedBookList().get(0).getAuthor());
        assertEquals("Title", response.getFoundedBookList().get(0).getTitle());
        verify(validator).validate(request);
        verify(validator).validate(any());
        verify(dataBase).findByTitle(request.getTitle());
        verify(dataBase).findByTitle(any());
    }

    @Test
    public void shouldReturnResponseWithErrorWhenPageSizeZero() {
        Paging paging = new Paging(1, 0);
        SearchBooksRequest request = new SearchBooksRequest("Author", "", null, paging);
        when(validator.validate(request)).thenReturn(List.of(new CoreError("PageSize", "Must be over 0")));
        SearchBooksResponse response = service.execute(request);

        assertTrue(response.hasError());
        assertEquals(1, response.getErrors().size());
        assertEquals("PageSize", response.getErrors().get(0).getField());
        assertEquals("Must be over 0", response.getErrors().get(0).getDescription());
        verify(validator).validate(any());
        verifyNoInteractions(dataBase);
    }

    @Test
    public void ShouldReturnResponseWithErrorsWhenPageSizeAndPageNumberZero() {
        Paging paging = new Paging(0, 0);
        SearchBooksRequest request = new SearchBooksRequest("Author", "", null, paging);
        when(validator.validate(request)).thenReturn(List.of(new CoreError("PageNumber", "Must be over 0"),
                new CoreError("PageSize", "Must be over 0")));
        SearchBooksResponse response = service.execute(request);

        assertTrue(response.hasError());
        assertEquals(2, response.getErrors().size());
        assertEquals("PageNumber", response.getErrors().get(0).getField());
        assertEquals("Must be over 0", response.getErrors().get(0).getDescription());
        assertEquals("PageSize", response.getErrors().get(1).getField());
        assertEquals("Must be over 0", response.getErrors().get(1).getDescription());
        verify(validator).validate(request);
        verifyNoInteractions(dataBase);
    }

}