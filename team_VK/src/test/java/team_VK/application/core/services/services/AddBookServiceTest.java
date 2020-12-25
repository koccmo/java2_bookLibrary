package team_VK.application.core.services.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.MockitoJUnitRunner;
import team_VK.application.core.requests.AddBookRequest;
import team_VK.application.core.responses.AddBookResponse;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.main_menu_services.AddBookService;
import team_VK.application.core.services.validators.AddBookServiceValidator;
import team_VK.application.core.services.matchers.BookMatcher;
import team_VK.application.database.Database;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
@RunWith(MockitoJUnitRunner.class)
public class AddBookServiceTest {

    @Mock private AddBookServiceValidator validator;
    @Mock private Database database;
    @InjectMocks AddBookService addBookService;


    @Test
    public void ShouldAddBookWhenResponseWithoutErrors() {
        List<CoreError> errors = new ArrayList<>();
        AddBookRequest request = new AddBookRequest("Foo", "Bar", 3);
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddBookResponse response = addBookService.addBook(request);
        Assert.assertEquals(response.errorList.size(), 0);
        Assert.assertFalse(response.havesError());
    }

    @Test
    public void ShouldAddBookWhenResponseWithErrors() {
        List<CoreError> errors = new ArrayList<>();
        AddBookRequest request = new AddBookRequest("Foo", "Bar", 3);
        errors.add(new CoreError("qox", "buz"));
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddBookResponse response = addBookService.addBook(request);

        Assert.assertEquals(response.errorList.size(), 1);
        Assert.assertTrue(response.havesError());
        Assert.assertEquals(response.errorList.get(0).getField(), "qox");
    }

    @Test
    public void ShouldAddBook() {
        List<CoreError> errors = new ArrayList<>();
        AddBookRequest request = new AddBookRequest("Title", "Author", 1);
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddBookResponse response = addBookService.addBook(request);

        Assert.assertEquals(response.getErrorList().size(), 0);
        Mockito.verify(database).addBook(argThat(new BookMatcher("Title", "Author", 1)));
        Mockito.verify(validator).validate(any());
    }

}


