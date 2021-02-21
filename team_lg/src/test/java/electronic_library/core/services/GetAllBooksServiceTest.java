package electronic_library.core.services;

import electronic_library.core.database.book.ElectronicLibraryRepository;
import electronic_library.core.domain.Book;
import electronic_library.core.requests.book.GetAllBooksRequest;
import electronic_library.core.responses.book.GetAllBooksResponse;
import electronic_library.core.services.book.GetAllBooksService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class GetAllBooksServiceTest {

    @Mock
    private ElectronicLibraryRepository electronicLibrary;

    @InjectMocks
    GetAllBooksService service;

    @Test
    public void shouldGetBooksFromElectronicLibrary() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("aaa", "aaa",new BigDecimal("10.00"),2010));
        books.add(new Book("bbb", "bbb",new BigDecimal("20.00"),2020));
        Mockito.when(electronicLibrary.getElectronicLibrary()).thenReturn(books);
        GetAllBooksRequest request = new GetAllBooksRequest();
        GetAllBooksResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals(response.getBooks().size(), 2);
        assertEquals(response.getBooks().get(0).getBookTitle(), "aaa");
        assertEquals(response.getBooks().get(0).getBookAuthor(), "aaa");
        assertEquals(response.getBooks().get(1).getBookTitle(), "bbb");
        assertEquals(response.getBooks().get(1).getBookAuthor(), "bbb");
    }
}