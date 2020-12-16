package team_VK.application.core.services.services;

//import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;
import team_VK.application.core.domain.Book;
import team_VK.application.core.requests.RemoveBookRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.RemoveBookServiceValidator;
import team_VK.application.database.DatabaseInMemory;

import java.util.ArrayList;
import java.util.List;

public class RemoveBookServiceValidatorTest {


    RemoveBookServiceValidator subject = new RemoveBookServiceValidator();
    List<Book> bookList = getBooks();
    DatabaseInMemory database = new DatabaseInMemory();



    @Test
    public void ShouldValidateCorrectBook() {
// positive functional test
        RemoveBookRequest request = new RemoveBookRequest(1, "foobar");

        List<CoreError> errorsActual = subject.validate(request, database);
        Assert.assertEquals(0, errorsActual.size());

    }


    @Test
    // negative. ID out of bound

    public void ShouldntValidate_incorrect_outOfBound_ID() {
        RemoveBookRequest request = new RemoveBookRequest(3, "foobar");
        List<CoreError> errorsActual = subject.validate(request, database);
        CoreError error = new CoreError("Book ID", "Not existing book ID entered" );
        List<CoreError> errorsExpected = List.of(error);

        Assert.assertEquals(errorsExpected, errorsActual);

    }

    @Test
    // negative. ID not consist to Book Title

    public void ShouldntValidate_bookID_doesnt_consist_to_Title() {
        RemoveBookRequest request = new RemoveBookRequest(2, "foobar");
        List<CoreError> errorsActual = subject.validate(request, database);
        CoreError error = new CoreError("Book ID", "ID not consist to Book Title" );
        List<CoreError> errorsExpected = List.of(error);

        Assert.assertEquals(errorsExpected, errorsActual);

    }


    private List<Book> getBooks() {
        List<Book> bookList = new ArrayList<>();
        Book book1 = new Book("foobar", "barfoo",3);
        book1.setID(1);
        bookList.add(book1);

        Book book2 = new Book("quzbuz", "buzqux",3);
        book2.setID(2);
        bookList.add(book2);
        return bookList;
    }
}


