package book_library.core.services;

import book_library.core.domain.Book;
import book_library.core.database.Database;
import book_library.core.requests.GetAllBooksRequest;
import book_library.core.responses.GetAllBooksResponse;
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
    private Database database;
    @InjectMocks
    private GetAllBooksService service;

    @Test
    public void shouldGetAllBooksFromDb() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Title", "Author"));
        Mockito.when(database.getAllBooks()).thenReturn(books);

        GetAllBooksRequest request = new GetAllBooksRequest();
        GetAllBooksResponse response = service.execute(request);
        Mockito.verify(database).getAllBooks();
        assertFalse(response.hasErrors());
        assertEquals(1, response.getBooks().size());
        assertEquals("Title", response.getBooks().get(0).getTitle());
        assertEquals("Author", response.getBooks().get(0).getAuthor());
    }

}