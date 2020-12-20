package team_VK.application.core.services.services;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import team_VK.application.core.domain.Book;
import team_VK.application.core.requests.RemoveBookRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.RemoveBookServiceValidator;
import team_VK.application.database.DatabaseInMemory;

import java.util.List;

public class RemoveBookServiceValidatorTest {

    DatabaseInMemory database;
    RemoveBookServiceValidator validator;

    @Before
    public void setup() {
        validator = new RemoveBookServiceValidator();
        database = new DatabaseInMemory();
        getBooks();
    }


    @Test
    public void ShouldValidateCorrectBook() {
// positive functional test
        RemoveBookRequest request = new RemoveBookRequest(1, "foobar");

        List<CoreError> errorsActual = validator.validate(request, database);
        Assert.assertEquals(0, errorsActual.size());

    }


    @Test
    // negative. ID out of bound

    public void ShouldntValidate_incorrect_outOfBound_ID() {
        RemoveBookRequest request = new RemoveBookRequest(3, "foobar");
        List<CoreError> errorsActual = validator.validate(request, database);
        CoreError error = new CoreError("Book ID", "Not existing book ID entered");
        List<CoreError> errorsExpected = List.of(error);

        Assert.assertEquals(errorsExpected, errorsActual);

    }

    @Test
    // negative. ID not consist to Book Title

    public void ShouldntValidate_bookID_doesnt_consist_to_Title() {
        RemoveBookRequest request = new RemoveBookRequest(2, "foobar");
        List<CoreError> errors = validator.validate(request, database);

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


