package team_VK.application.core.services.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import team_VK.application.core.requests.AddBookRequest;
import team_VK.application.core.responses.AddBookResponse;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.AddBookService;
import team_VK.application.core.services.AddBookServiceValidator;
import team_VK.application.core.services.matchers.BookMatcher;
import team_VK.application.database.Database;
import team_VK.application.database.DatabaseInMemory;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;

public class AddBookServiceTest {

    private AddBookServiceValidator validator;
    private DatabaseInMemory database;
    private List<CoreError> errors;


    @Before
    public void setup() {
        database = new DatabaseInMemory();
        validator = Mockito.mock(AddBookServiceValidator.class);
        errors = new ArrayList<>();
    }

    @Test
    public void ShouldAddBookWhenResponseWithoutErrors() {

        AddBookRequest request = new AddBookRequest("Foo", "Bar", 3);

        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddBookService addBookService = new AddBookService(database, validator);

        AddBookResponse response = addBookService.addBook(request);
        Assert.assertEquals(response.errorList.size(), 0);
        Assert.assertFalse(response.havesError());
    }

    @Test
    public void ShouldAddBookWhenResponseWithErrors() {

        AddBookRequest request = new AddBookRequest("Foo", "Bar", 3);
        errors.add(new CoreError("qox", "buz"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddBookService addBookService = new AddBookService(database, validator);

        AddBookResponse response = addBookService.addBook(request);

        Assert.assertEquals(response.errorList.size(), 1);
        Assert.assertTrue(response.havesError());
        Assert.assertEquals(response.errorList.get(0).getField(), "qox");
    }

    @Test
    public void ShouldAddBook() {

        AddBookRequest request = new AddBookRequest("Title", "Author", 1);
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddBookService service = new AddBookService(database, validator);
        AddBookResponse response = service.addBook(request);

        Assert.assertEquals(response.getErrorList().size(), 0);
        Assert.assertEquals(database.getListBooks().size(), 1);
        Assert.assertEquals(database.getListBooks().get(0).bookAuthor, "Author");
    }
}