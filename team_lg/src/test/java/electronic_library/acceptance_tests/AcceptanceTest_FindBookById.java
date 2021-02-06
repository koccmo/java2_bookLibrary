package electronic_library.acceptance_tests;

import electronic_library.DatabaseCleaner;
import electronic_library.config.BookListConfiguration;
import electronic_library.core.domain.Book;
import electronic_library.core.requests.book.AddBookRequest;
import electronic_library.core.requests.book.FindBookByIdRequest;
import electronic_library.core.responses.book.FindBookByIdResponse;
import electronic_library.core.services.book.AddBookService;
import electronic_library.core.services.book.FindBookByIdService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.Assert.*;

@Profile("orm")
public class AcceptanceTest_FindBookById {

    private AnnotationConfigApplicationContext applicationContext;

    @Before
    public void setUp() {
        applicationContext = new AnnotationConfigApplicationContext(BookListConfiguration.class);
        getDatabaseCleaner().clean();
        AddBookRequest addBookRequestOne = new AddBookRequest("aaa", "aaa", new BigDecimal("10.00"), 2010);
        getAddBookService().execute(addBookRequestOne);
        AddBookRequest addBookRequestTwo = new AddBookRequest("bbb", "bbb", new BigDecimal("11.00"), 2011);
        getAddBookService().execute(addBookRequestTwo);
    }

    @Test
    public void FindBookByIdWithoutErrors() {

        FindBookByIdRequest request = new FindBookByIdRequest("2");
        FindBookByIdResponse response = getFindBookByIdService().findBookByIdResponse(request);

        assertFalse(response.hasErrors());
        assertFalse(response.getFindBookById().isEmpty());
        assertEquals(Optional.of(
                newBook(2L,"bbb","bbb",new BigDecimal("11.00"), 2011)),
                response.getFindBookById());
    }

    @Test
    public void FindBookByEmptyIdWithError() {

        FindBookByIdRequest request = new FindBookByIdRequest("");
        FindBookByIdResponse response = getFindBookByIdService().findBookByIdResponse(request);

        assertTrue(response.hasErrors());
        assertEquals(1, response.getErrors().size());
        assertEquals("Book ID", response.getErrors().get(0).getErrorField());
        assertEquals("not be empty.", response.getErrors().get(0).getErrorMessage());
    }

    @Test
    public void FindBookByIdWithErrorWhenNotId() {

        FindBookByIdRequest request = new FindBookByIdRequest("10");
        FindBookByIdResponse response = getFindBookByIdService().findBookByIdResponse(request);

        assertFalse(response.hasErrors());
        assertEquals(Optional.empty(),
                response.getFindBookById());
    }

    private Book newBook(Long id, String bookTitle, String bookAuthor, BigDecimal bookPrice, int yearOfBookIssue) {
        Book book = new Book(bookTitle, bookAuthor, bookPrice, yearOfBookIssue);
        book.setId(id);
        return book;
    }

    private AddBookService getAddBookService() { return applicationContext.getBean(AddBookService.class); }
    private FindBookByIdService getFindBookByIdService() { return applicationContext.getBean(FindBookByIdService.class); }
    private DatabaseCleaner getDatabaseCleaner() {
        return applicationContext.getBean(DatabaseCleaner.class);
    }

}
