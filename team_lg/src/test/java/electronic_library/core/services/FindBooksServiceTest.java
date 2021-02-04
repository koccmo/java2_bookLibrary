package electronic_library.core.services;

import electronic_library.core.database.book.ElectronicLibraryRepository;
import electronic_library.core.domain.Book;
import electronic_library.core.requests.book.FindBooksRequest;
import electronic_library.core.requests.Ordering;
import electronic_library.core.requests.Paging;
import electronic_library.core.responses.CoreError;
import electronic_library.core.responses.book.FindBooksResponse;
import electronic_library.core.services.book.FindBooksService;
import electronic_library.core.services.book.validators.FindBooksRequestValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class FindBooksServiceTest {

    @Mock
    private ElectronicLibraryRepository electronicLibrary;

    @Mock
    private FindBooksRequestValidator validator;

    @InjectMocks
    FindBooksService service;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidatorNotValid() {
        FindBooksRequest request = new FindBooksRequest(null, null);
        List<CoreError> errors = new ArrayList<>();
        errors.add(new CoreError("bookTitle", "Must not be empty!"));
        errors.add(new CoreError("bookAuthor", "Must not be empty!"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        FindBooksResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(response.getErrors().size(), 2);

        Mockito.verify(validator).validate(request);
        Mockito.verify(validator).validate(any());
        Mockito.verifyNoInteractions(electronicLibrary);
    }

    @Test
    public void shouldFindByTitle() {
        FindBooksRequest request = new FindBooksRequest("aaa", null);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Book> books = new ArrayList<>();
        books.add(new Book("aaa", "aaa", new BigDecimal("10.00"), 2001));
        Mockito.when(electronicLibrary.findBookByTitle("aaa")).thenReturn(books);

        FindBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getBooks().size(), 1);
        Assert.assertEquals(response.getBooks().get(0).getBookTitle(), "aaa");
        Assert.assertEquals(response.getBooks().get(0).getBookAuthor(), "aaa");
    }

    @Test
    public void shouldFindByAuthor() {
        FindBooksRequest request = new FindBooksRequest(null, "aaa");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Book> books = new ArrayList<>();
        books.add(new Book("aaa", "aaa", new BigDecimal("10.00"), 2001));
        Mockito.when(electronicLibrary.findBookByAuthor("aaa")).thenReturn(books);

        FindBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getBooks().size(), 1);
        Assert.assertEquals(response.getBooks().get(0).getBookTitle(), "aaa");
        Assert.assertEquals(response.getBooks().get(0).getBookAuthor(), "aaa");
    }

    @Test
    public void shouldSearchByTitleAndAuthor() {
        FindBooksRequest request = new FindBooksRequest("aaa", "aaa");
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Book> books = new ArrayList<>();
        books.add(new Book("aaa", "aaa", new BigDecimal("10.00"), 2001));
        Mockito.when(electronicLibrary.findByTitleAndAuthor("aaa", "aaa")).thenReturn(books);

        FindBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getBooks().size(), 1);
        Assert.assertEquals(response.getBooks().get(0).getBookTitle(), "aaa");
        Assert.assertEquals(response.getBooks().get(0).getBookAuthor(), "aaa");
    }

    @Test
    public void shouldFindByTitleWithOrderingAscending() {
        Ordering ordering = new Ordering("author", "ASC");
        FindBooksRequest request = new FindBooksRequest("aaa", null, ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Book> books = new ArrayList<>();
        books.add(new Book("bbb", "bbb", new BigDecimal("20.00"), 2002));
        books.add(new Book("aaa", "aaa", new BigDecimal("10.00"), 2001));
        Mockito.when(electronicLibrary.findBookByTitle("aaa")).thenReturn(books);

        FindBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getBooks().size(), 2);
        Assert.assertEquals(response.getBooks().get(0).getBookAuthor(), "aaa");
        Assert.assertEquals(response.getBooks().get(1).getBookAuthor(), "bbb");
    }

    @Test
    public void shouldFindByTitleWithOrderingDescending() {
        Ordering ordering = new Ordering("author", "DESC");
        FindBooksRequest request = new FindBooksRequest("aaa", null, ordering);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Book> books = new ArrayList<>();
        books.add(new Book("aaa", "aaa", new BigDecimal("10.00"), 2001));
        books.add(new Book("bbb", "bbb", new BigDecimal("20.00"), 2002));
        Mockito.when(electronicLibrary.findBookByTitle("aaa")).thenReturn(books);

        FindBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getBooks().size(), 2);
        Assert.assertEquals(response.getBooks().get(0).getBookAuthor(), "bbb");
        Assert.assertEquals(response.getBooks().get(1).getBookAuthor(), "aaa");
    }

    @Test
    public void shouldFindByTitleWithPagingFirstPage() {
        Paging paging = new Paging(1, 1);
        FindBooksRequest request = new FindBooksRequest("aaa", null, null, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Book> books = new ArrayList<>();
        books.add(new Book("aaa", "aaa", new BigDecimal("10.00"), 2001));
        books.add(new Book("bbb", "bbb", new BigDecimal("20.00"), 2002));
        Mockito.when(electronicLibrary.findBookByTitle("aaa")).thenReturn(books);

        FindBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getBooks().size(), 1);
        Assert.assertEquals(response.getBooks().get(0).getBookTitle(), "aaa");
        Assert.assertEquals(response.getBooks().get(0).getBookAuthor(), "aaa");
    }

    @Test
    public void shouldFindByTitleWithPagingSecondPage() {
        Paging paging = new Paging(2, 1);
        FindBooksRequest request = new FindBooksRequest("bbb", null, null, paging);
        Mockito.when(validator.validate(request)).thenReturn(new ArrayList<>());

        List<Book> books = new ArrayList<>();
        books.add(new Book("aaa", "aaa", new BigDecimal("10.00"), 2001));
        books.add(new Book("bbb", "bbb", new BigDecimal("20.00"), 2002));
        Mockito.when(electronicLibrary.findBookByTitle("bbb")).thenReturn(books);

        FindBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getBooks().size(), 1);
        Assert.assertEquals(response.getBooks().get(0).getBookTitle(), "bbb");
        Assert.assertEquals(response.getBooks().get(0).getBookAuthor(), "bbb");
    }
}