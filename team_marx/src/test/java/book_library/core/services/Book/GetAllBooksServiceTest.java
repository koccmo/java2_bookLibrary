package book_library.core.services.Book;

import book_library.core.domain.Book;
import book_library.core.database.Book.BookRepository;
import book_library.core.requests.Book.GetAllBooksRequest;
import book_library.core.responses.Book.GetAllBooksResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class GetAllBooksServiceTest {

    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private GetAllBooksService service;

    @Test
    public void shouldGetAllBooksFromDb() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Title", "Author"));
        Mockito.when(bookRepository.getAllBooks()).thenReturn(books);

        GetAllBooksRequest request = new GetAllBooksRequest();
        GetAllBooksResponse response = service.execute(request);
        Mockito.verify(bookRepository).getAllBooks();
        assertFalse(response.hasErrors());
        assertEquals(1, response.getBooks().size());
        assertEquals("Title", response.getBooks().get(0).getTitle());
        assertEquals("Author", response.getBooks().get(0).getAuthor());
    }

}