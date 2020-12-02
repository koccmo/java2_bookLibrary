package team_VK.application.core.services.services;

import org.junit.Assert;
import org.junit.Test;
import team_VK.application.core.requests.AddBookRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.AddBookServiceValidator;

import java.util.ArrayList;
import java.util.List;

public class AddBookServiceValidatorTest {

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