package team_VK.application.core.services.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mockito;

import team_VK.application.ApplicationContext;
import team_VK.application.core.requests.AddBookRequest;
import team_VK.application.core.responses.AddBookResponse;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.AddBookService;
import team_VK.application.core.services.AddBookServiceValidator;
import team_VK.application.core.services.matchers.BookMatcher;
import team_VK.application.database.Database;
import team_VK.application.database.DatabaseInMemory;
import team_VK.application.dependenci_injection.DIApplicationContextBuilder;


import java.io.IOException;
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
    DIApplicationContextBuilder diApplicationContextBuilder = new DIApplicationContextBuilder();
    try {
        ApplicationContext applicationContext = diApplicationContextBuilder.build("team_VK.application");
    }
    catch (IOException e){new RuntimeException("Classes not found");}
    catch (ClassNotFoundException e) {new RuntimeException("Classes not found");}
//        database = new DatabaseInMemory();
//        validator = Mockito.mock(AddBookServiceValidator.class);
//        errors = new ArrayList<>();
    }

    @Test
    public void ShouldAddBookWhenResponseWithoutErrors() {

        AddBookRequest request = new AddBookRequest("Foo", "Bar", 3);

        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddBookService addBookService = new AddBookService();

        AddBookResponse response = addBookService.addBook(request);
        Assert.assertEquals(response.errorList.size(), 0);
        Assert.assertFalse(response.havesError());
    }

    @Test
    public void ShouldAddBookWhenResponseWithErrors() {

        AddBookRequest request = new AddBookRequest("Foo", "Bar", 3);
        errors.add(new CoreError("qox", "buz"));
        Mockito.when(validator.validate(request)).thenReturn(errors);
        AddBookService addBookService = new AddBookService();

        AddBookResponse response = addBookService.addBook(request);

        Assert.assertEquals(response.errorList.size(), 1);
        Assert.assertTrue(response.havesError());
        Assert.assertEquals(response.errorList.get(0).getField(), "qox");
    }

    @Test
    public void ShouldAddBook() {

        AddBookRequest request = new AddBookRequest("Title", "Author", 1);
        Mockito.when(validator.validate(request)).thenReturn(errors);

        AddBookService service = new AddBookService();
        AddBookResponse response = service.addBook(request);

        Assert.assertEquals(response.getErrorList().size(), 0);
        Assert.assertEquals(database.getListBooks().size(), 1);
        Assert.assertEquals(database.getListBooks().get(0).bookAuthor, "Author");
    }



    @Test
    public void ShouldValidateCorrectBookAuthor() {
// positive functional test

        AddBookRequest addBookRequest = new AddBookRequest("The Old Mann and Sea", "Ernest Hemingway",10);
        List<CoreError> errors ;

        AddBookServiceValidator subject = new AddBookServiceValidator();
        errors = subject.validate(addBookRequest);
        Assert.assertEquals(0, errors.size());

    }

    @Test
    public void ShouldntValidate_too_short_BookAuthor() {
// negative. bookAuthor less then 3 letter // boundary test

        AddBookRequest addBookRequest = new AddBookRequest("The Old Mann and Sea", "Er",10);
        List<CoreError> errorsActual ;
        List<CoreError> errorsExpected = new ArrayList<>();
        CoreError error = new CoreError("bookAuthor", "Field bookAuthor is too short");
        errorsExpected.add(error);
        AddBookServiceValidator subject = new AddBookServiceValidator();
        errorsActual = subject.validate(addBookRequest);
        Assert.assertEquals(errorsExpected, errorsActual);

    }

    @Test
    public void ShouldntValidate_space_BookAuthor() {
        // negative. bookAuthor contains only Spaces // boundary test

        AddBookRequest addBookRequest = new AddBookRequest("The Old Mann and Sea", "  ",10);
        List<CoreError> errorsActual ;
        List<CoreError> errorsExpected = new ArrayList<>();
        CoreError error = new CoreError("bookAuthor", "Field bookAuthor can't be Space");
        errorsExpected.add(error);
        AddBookServiceValidator subject = new AddBookServiceValidator();
        errorsActual = subject.validate(addBookRequest);
        Assert.assertEquals(errorsExpected, errorsActual);

    }

    @Test
    public void ShouldntValidate_illegalCharacters_BookAuthor() {
// negative. bookAuthor contains illegal character with code 31 // boundary test

        AddBookRequest addBookRequest = new AddBookRequest("The Old Mann and Sea", "Ernest Hemingway!",10);
        List<CoreError> errorsActual;
        List<CoreError> errorsExpected = new ArrayList<>();
        CoreError error = new CoreError("bookAuthor", "Field bookAuthor contains illegal characters");
        errorsExpected.add(error);
        AddBookServiceValidator subject = new AddBookServiceValidator();
        errorsActual = subject.validate(addBookRequest);
        Assert.assertEquals(errorsExpected, errorsActual);

    }




    @Test
    public void ShouldValidateCorrectBookTitle() {
// positive functional test

        AddBookRequest addBookRequest = new AddBookRequest("The Old Mann and Sea", "Ernest Hemingway",10);
        List<CoreError> errors ;

        AddBookServiceValidator subject = new AddBookServiceValidator();
        errors = subject.validate(addBookRequest);
        Assert.assertEquals(0, errors.size());

    }

    @Test
    public void ShouldntValidate_too_short_BookTitle() {
// negative. bookTitle contains less then 3 letter // boundary test

        AddBookRequest addBookRequest = new AddBookRequest("Th", "Ernest Hemingway",10);
        List<CoreError> errorsActual ;
        List<CoreError> errorsExpected = new ArrayList<>();
        CoreError error = new CoreError("bookTitle", "Field bookTitle is too short");
        errorsExpected.add(error);
        AddBookServiceValidator subject = new AddBookServiceValidator();
        errorsActual = subject.validate(addBookRequest);
        Assert.assertEquals(errorsExpected, errorsActual);

    }

    @Test
    public void ShouldntValidate_space_BookTitle() {
        // negative. bookTitle contains only Spaces // boundary test

        AddBookRequest addBookRequest = new AddBookRequest("  ", "Ernest Hemingway",10);
        List<CoreError> errorsActual ;
        List<CoreError> errorsExpected = new ArrayList<>();
        CoreError error = new CoreError("bookTitle", "Field bookTitle can't be Space");
        errorsExpected.add(error);
        AddBookServiceValidator subject = new AddBookServiceValidator();
        errorsActual = subject.validate(addBookRequest);
        Assert.assertEquals(errorsExpected, errorsActual);

    }

    @Test
    public void ShouldntValidate_illegalCharacters_BookTitle() {
// negative. bookAuthor contains illegal character with code 35 // boundary test


        AddBookRequest addBookRequest = new AddBookRequest("The Old Mann and S#ea", "Ernest Hemingway", 10);
        List<CoreError> errorsActual;
        List<CoreError> errorsExpected = new ArrayList<>();
        CoreError error = new CoreError("bookTitle", "Field bookTitle contains illegal characters");
        errorsExpected.add(error);
        AddBookServiceValidator subject = new AddBookServiceValidator();
        errorsActual = subject.validate(addBookRequest);
        Assert.assertEquals(errorsExpected, errorsActual);

    }







}