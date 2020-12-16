package team_VK.application.core.services.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import team_VK.application.ApplicationContext;
import team_VK.application.core.domain.Book;
import team_VK.application.core.requests.RemoveBookRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.responses.RemoveBookResponse;
import team_VK.application.core.services.RemoveBookService;
import team_VK.application.core.services.RemoveBookServiceValidator;
import team_VK.application.core.services.matchers.BookMatcher;
import team_VK.application.database.DataBaseFiller;
import team_VK.application.database.Database;
import team_VK.application.database.DatabaseInMemory;
import team_VK.application.dependenci_injection.DIApplicationContextBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RemoveBookServiceTest {

    private RemoveBookServiceValidator validator;
    private DatabaseInMemory database ;
    private List<CoreError> errors;
    private RemoveBookService service;
    private ApplicationContext context;

    @Before
    public void setup() throws IOException, ClassNotFoundException {
        context = new DIApplicationContextBuilder().build("team_VK.application");
        DataBaseFiller dataBaseFiller = context.getBean(DataBaseFiller.class);
        dataBaseFiller.fill();

        database = context.getBean(DatabaseInMemory.class);
        validator = Mockito.mock(RemoveBookServiceValidator.class);
        service = context.getBean( RemoveBookService.class);
        errors = new ArrayList<>();
    }

    @Test
    public void ShouldReturnNoErrorsWhenErrorsListIsEmpty() {

        RemoveBookRequest request = new RemoveBookRequest(1, "The Old Man and Sea");

        Mockito.when(validator.validate(request, database)).thenReturn(errors);


        RemoveBookResponse response = service.removeBook(request);
        Assert.assertEquals(response.getErrorList().size(), 0);
        Assert.assertFalse(response.havesError());
    }

    @Test
    public void ShouldReturnErrorWhenErrorsListNotEmpty() {

        RemoveBookRequest request = new RemoveBookRequest(1, "The Old Man and Sea1");
        errors.add(new CoreError("Book ID", "ID not consist to Book Title"));
        Mockito.when(validator.validate(request, database)).thenReturn(errors);
        RemoveBookService service = new RemoveBookService();

        RemoveBookResponse response = service.removeBook(request);

        Assert.assertEquals(response.getErrorList().size(), 6);
        Assert.assertTrue(response.havesError());
        Assert.assertEquals(response.getErrorList().get(0).getField(), "Book ID");
    }

    @Test
    public void ShouldRemoveBookByID() {

        RemoveBookRequest request = new RemoveBookRequest(1, "The Old Man and Sea");
        Mockito.when(validator.validate(request, database)).thenReturn(errors);

        RemoveBookService service = new RemoveBookService();
        RemoveBookResponse response = service.removeBook(request);

        Assert.assertEquals(response.getErrorList().size(), 0);
        Assert.assertEquals(database.getListBooks().size(), 5);
    }
}
