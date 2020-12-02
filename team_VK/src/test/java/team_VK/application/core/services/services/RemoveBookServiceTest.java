package team_VK.application.core.services.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import team_VK.application.core.domain.Book;
import team_VK.application.core.requests.RemoveBookRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.responses.RemoveBookResponse;
import team_VK.application.core.services.RemoveBookService;
import team_VK.application.core.services.RemoveBookServiceValidator;
import team_VK.application.core.services.matchers.BookMatcher;
import team_VK.application.database.Database;
import team_VK.application.database.DatabaseInMemory;

import java.util.ArrayList;
import java.util.List;

public class RemoveBookServiceTest {

    private RemoveBookServiceValidator validator;
    private DatabaseInMemory database ;
    private List<CoreError> errors;


    @Before
    public void setup() {
        database = new DatabaseInMemory();
        database.addBook(new Book("Title", "Author", 5));
        validator = Mockito.mock(RemoveBookServiceValidator.class);
        errors = new ArrayList<>();
    }

    @Test
    public void ShouldReturnNoErrorsWhenErrorsListIsEmpty() {

        RemoveBookRequest request = new RemoveBookRequest(1, "Title");

        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        RemoveBookService service = new RemoveBookService(database, validator);

        RemoveBookResponse response = service.removeBook(request);
        Assert.assertEquals(response.getErrorList().size(), 0);
        Assert.assertFalse(response.havesError());
    }

    @Test
    public void ShouldReturnErrorWhenErrorsListNotEmpty() {

        RemoveBookRequest request = new RemoveBookRequest(1, "Title1");
        errors.add(new CoreError("Book ID", "ID not consist to Book Title"));
        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        RemoveBookService service = new RemoveBookService(database, validator);

        RemoveBookResponse response = service.removeBook(request);

        Assert.assertEquals(response.getErrorList().size(), 1);
        Assert.assertTrue(response.havesError());
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Book ID");
    }

    @Test
    public void ShouldRemoveBookByID() {

        RemoveBookRequest request = new RemoveBookRequest(1, "Title");
        Mockito.when(validator.validate(request, database)).thenReturn(errors);

        RemoveBookService service = new RemoveBookService(database, validator);
        RemoveBookResponse response = service.removeBook(request);

        Assert.assertEquals(response.getErrorList().size(), 0);
        Assert.assertEquals(database.getListBooks().size(), 0);
    }
}
