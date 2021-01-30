package team_VK.application.core.services.services;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import team_VK.application.configuration.LibraryConfig;
import team_VK.application.core.domain.Book;
import team_VK.application.core.requests.RemoveBookRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.validators.RemoveBookServiceValidator;
import team_VK.application.database.Database;

import java.util.List;

public class RemoveBookServiceValidatorTest {
@Autowired
    Database database;
    RemoveBookServiceValidator subject;
    private ApplicationContext appContext;
    @Before
    public void setup() {
        appContext = new AnnotationConfigApplicationContext(LibraryConfig.class);
        subject = appContext.getBean(RemoveBookServiceValidator.class);
        database = appContext.getBean(Database.class);
        getBooks();
    }


    @Test
    public void ShouldValidateCorrectBook() {
// positive functional test
        RemoveBookRequest request = new RemoveBookRequest(1, "The Old Man and Sea");

        List<CoreError> errorsActual = subject.validate(request, database);
        Assert.assertEquals(0, errorsActual.size());

    }


    @Test
    // negative. ID out of bound

    public void ShouldntValidate_incorrect_outOfBound_ID() {
        RemoveBookRequest request = new RemoveBookRequest(100, "foobar");
        List<CoreError> errorsActual = subject.validate(request, database);
        CoreError error = new CoreError("Book ID", "Not existing book ID entered");
        List<CoreError> errorsExpected = List.of(error);

        Assert.assertEquals(errorsExpected, errorsActual);

    }

    @Test
    // negative. ID not consist to Book Title

    public void ShouldntValidate_bookID_doesnt_consist_to_Title() {
        RemoveBookRequest request = new RemoveBookRequest(2, "foobar");
        List<CoreError> errors = subject.validate(request, database);

        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).getErrorMessage(), "ID not consist to Book Title");
        Assert.assertEquals(errors.get(0).getField(),"Book ID");
    }


    private void getBooks() {
        Book book1 = new Book("foobar", "barfoo", 3);
        database.addBook(book1);
        Book book2 = new Book("quzbuz", "buzqux", 3);
        database.addBook(book2);

    }
}


