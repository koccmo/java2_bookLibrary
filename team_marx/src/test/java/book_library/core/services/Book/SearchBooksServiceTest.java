package book_library.core.services.Book;

import book_library.core.domain.Book;
import book_library.core.database.Book.BookRepository;
import book_library.core.requests.Ordering;
import book_library.core.requests.Paging;
import book_library.core.requests.Book.SearchBooksRequest;
import book_library.core.responses.CoreError;
import book_library.core.responses.Book.SearchBooksResponse;
import book_library.core.services.Book.SearchBooksService;
import book_library.core.validators.Book.SearchBooksRequestValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class SearchBooksServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private SearchBooksRequestValidator validator;
    @InjectMocks
    private SearchBooksService service;

    @Before
    public void setup(){
        ReflectionTestUtils.setField(service,"orderingEnabled", true);
        ReflectionTestUtils.setField(service,"pagingEnabled", true);
    }

    @Test
    public void shouldReturnResponseWithErrorWhenValidatorFails() {
        SearchBooksRequest request = new SearchBooksRequest(null, null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("title", "Must not be empty!"));
        errors.add(new CoreError("author", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        SearchBooksResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(2, response.getErrors().size());
        assertEquals("title", response.getErrors().get(0).getField());
        assertEquals("Must not be empty!", response.getErrors().get(0).getMessage());
        assertEquals("author", response.getErrors().get(1).getField());
        assertEquals("Must not be empty!", response.getErrors().get(1).getMessage());

        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
        Mockito.verifyNoInteractions(bookRepository);
    }

    @Test
    public void shouldSearchByTitle() {
        SearchBooksRequest request = new SearchBooksRequest("Title", null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Title", "Author"));
        Mockito.when(bookRepository.findByTitle("Title")).thenReturn(books);

        SearchBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(1, response.getBooks().size());
        assertEquals("Title", response.getBooks().get(0).getTitle());
        assertEquals("Author", response.getBooks().get(0).getAuthor());
    }

    @Test
    public void shouldSearchByAuthor() {
        SearchBooksRequest request = new SearchBooksRequest(null, "Author");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Title", "Author"));
        Mockito.when(bookRepository.findByAuthor("Author")).thenReturn(books);

        SearchBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(1, response.getBooks().size());
        assertEquals("Title", response.getBooks().get(0).getTitle());
        assertEquals("Author", response.getBooks().get(0).getAuthor());
    }

    @Test
    public void shouldSearchByTitleAndAuthor() {
        SearchBooksRequest request = new SearchBooksRequest("Title", "Author");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Title", "Author"));
        Mockito.when(bookRepository.findByTitleAndAuthor("Title", "Author")).thenReturn(books);

        SearchBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(1, response.getBooks().size());
        assertEquals("Title", response.getBooks().get(0).getTitle());
        assertEquals("Author", response.getBooks().get(0).getAuthor());
    }

    @Test
    public void shouldSearchByAuthorWithOrderingAscendingByTitle() {
        Ordering ordering = new Ordering("title", "ASCENDING");
        SearchBooksRequest request = new SearchBooksRequest(null, "Author", ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Title2", "Author"));
        books.add(new Book("Title1", "Author"));
        books.add(new Book("Title3", "Author"));
        Mockito.when(bookRepository.findByAuthor("Author")).thenReturn(books);

        SearchBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(3, response.getBooks().size());
        assertEquals("Title1", response.getBooks().get(0).getTitle());
        assertEquals("Author", response.getBooks().get(0).getAuthor());
        assertEquals("Title2", response.getBooks().get(1).getTitle());
        assertEquals("Author", response.getBooks().get(1).getAuthor());
        assertEquals("Title3", response.getBooks().get(2).getTitle());
        assertEquals("Author", response.getBooks().get(2).getAuthor());
    }

    @Test
    public void shouldSearchByAuthorWithOrderingDescendingByTitle() {
        Ordering ordering = new Ordering("title", "DESCENDING");
        SearchBooksRequest request = new SearchBooksRequest(null, "Author", ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Title3", "Author"));
        books.add(new Book("Title1", "Author"));
        books.add(new Book("Title2", "Author"));
        Mockito.when(bookRepository.findByAuthor("Author")).thenReturn(books);

        SearchBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(3, response.getBooks().size());
        assertEquals("Title3", response.getBooks().get(0).getTitle());
        assertEquals("Author", response.getBooks().get(0).getAuthor());
        assertEquals("Title2", response.getBooks().get(1).getTitle());
        assertEquals("Author", response.getBooks().get(1).getAuthor());
        assertEquals("Title1", response.getBooks().get(2).getTitle());
        assertEquals("Author", response.getBooks().get(2).getAuthor());
    }

    @Test
    public void shouldSearchByTitleWithOrderingAscendingByAuthor() {
        Ordering ordering = new Ordering("author", "ASCENDING");
        SearchBooksRequest request = new SearchBooksRequest("Title", null, ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Title", "Author2"));
        books.add(new Book("Title", "Author1"));
        books.add(new Book("Title", "Author3"));
        Mockito.when(bookRepository.findByTitle("Title")).thenReturn(books);

        SearchBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(3, response.getBooks().size());
        assertEquals("Title", response.getBooks().get(0).getTitle());
        assertEquals("Author1", response.getBooks().get(0).getAuthor());
        assertEquals("Title", response.getBooks().get(1).getTitle());
        assertEquals("Author2", response.getBooks().get(1).getAuthor());
        assertEquals("Title", response.getBooks().get(2).getTitle());
        assertEquals("Author3", response.getBooks().get(2).getAuthor());
    }

    @Test
    public void shouldSearchByTitleWithOrderingDescendingByAuthor() {
        Ordering ordering = new Ordering("author", "DESCENDING");
        SearchBooksRequest request = new SearchBooksRequest("Title", null, ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Title", "Author3"));
        books.add(new Book("Title", "Author1"));
        books.add(new Book("Title", "Author2"));
        Mockito.when(bookRepository.findByTitle("Title")).thenReturn(books);

        SearchBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(3, response.getBooks().size());
        assertEquals("Title", response.getBooks().get(0).getTitle());
        assertEquals("Author3", response.getBooks().get(0).getAuthor());
        assertEquals("Title", response.getBooks().get(1).getTitle());
        assertEquals("Author2", response.getBooks().get(1).getAuthor());
        assertEquals("Title", response.getBooks().get(2).getTitle());
        assertEquals("Author1", response.getBooks().get(2).getAuthor());
    }

    @Test
    public void shouldSearchByTitleWithPagingFirstPage(){
        Paging paging = new Paging(1, 2);
        SearchBooksRequest request = new SearchBooksRequest("Title", null, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Title", "Author1"));
        books.add(new Book("Title", "Author2"));
        books.add(new Book("Title", "Author3"));
        Mockito.when(bookRepository.findByTitle("Title")).thenReturn(books);

        SearchBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(2, response.getBooks().size());
        assertEquals("Title", response.getBooks().get(0).getTitle());
        assertEquals("Author1", response.getBooks().get(0).getAuthor());
        assertEquals("Title", response.getBooks().get(1).getTitle());
        assertEquals("Author2", response.getBooks().get(1).getAuthor());
    }

    @Test
    public void shouldSearchByTitleWithPagingSecondPage(){
        Paging paging = new Paging(2, 2);
        SearchBooksRequest request = new SearchBooksRequest("Title", null, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Book> books = new ArrayList<>();
        books.add(new Book("Title", "Author1"));
        books.add(new Book("Title", "Author2"));
        books.add(new Book("Title", "Author3"));
        Mockito.when(bookRepository.findByTitle("Title")).thenReturn(books);

        SearchBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(1, response.getBooks().size());
        assertEquals("Title", response.getBooks().get(0).getTitle());
        assertEquals("Author3", response.getBooks().get(0).getAuthor());
    }
}