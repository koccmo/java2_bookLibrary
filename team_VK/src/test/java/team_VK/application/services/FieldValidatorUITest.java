package team_VK.application.services;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import team_VK.application.requests.AddBookRequest;
import team_VK.application.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class FieldValidatorUITest {

    @Test
    public void ShouldValidateCorrectBookAuthor() {

        AddBookRequest addBookRequest = new AddBookRequest("The Old Mann and Sea", "Ernest Hemingway");
        List<CoreError> errors = new ArrayList<>();

        FieldValidatorUI subject = new FieldValidatorUI();
        errors = subject.bookAuthorFieldValidate(addBookRequest, errors);
        Assert.assertTrue(errors.size() == 0);

    }

    @Test
    public void ShouldntValidate_too_short_BookAuthor() {

        AddBookRequest addBookRequest = new AddBookRequest("The Old Mann and Sea", "Er");
        List<CoreError> errorsActual = new ArrayList<>();
        List<CoreError> errorsExpected = new ArrayList<>();
        CoreError error = new CoreError("bookAuthor", "Field bookAuthor is too short");
        errorsExpected.add(error);
        FieldValidatorUI subject = new FieldValidatorUI();
        errorsActual = subject.bookAuthorFieldValidate(addBookRequest, errorsActual);
        Assert.assertEquals(errorsExpected, errorsActual);

    }

    @Test
    public void ShouldntValidate_space_BookAuthor() {

        AddBookRequest addBookRequest = new AddBookRequest("The Old Mann and Sea", "  ");
        List<CoreError> errorsActual = new ArrayList<>();
        List<CoreError> errorsExpected = new ArrayList<>();
        CoreError error = new CoreError("bookAuthor", "Field bookAuthor can't be Space");
        errorsExpected.add(error);
        FieldValidatorUI subject = new FieldValidatorUI();
        errorsActual = subject.bookAuthorFieldValidate(addBookRequest, errorsActual);
        Assert.assertEquals(errorsExpected, errorsActual);

    }

    @Test
    public void ShouldntValidate_illegalCharacters_BookAuthor() {

        AddBookRequest addBookRequest = new AddBookRequest("The Old Mann and Sea", "Ernest Hemingway!");
        List<CoreError> errorsActual = new ArrayList<>();
        List<CoreError> errorsExpected = new ArrayList<>();
        CoreError error = new CoreError("bookAuthor", "Field bookAuthor contains illegal characters");
        errorsExpected.add(error);
        FieldValidatorUI subject = new FieldValidatorUI();
        errorsActual = subject.bookAuthorFieldValidate(addBookRequest, errorsActual);
        Assert.assertEquals(errorsExpected, errorsActual);

    }




    @Test
    public void ShouldValidateCorrectBookTitle() {

        AddBookRequest addBookRequest = new AddBookRequest("The Old Mann and Sea", "Ernest Hemingway");
        List<CoreError> errors = new ArrayList<>();

        FieldValidatorUI subject = new FieldValidatorUI();
        errors = subject.bookTitleFieldValidate(addBookRequest, errors);
        Assert.assertTrue(errors.size() == 0);

    }

    @Test
    public void ShouldntValidate_too_short_BookTitle() {

        AddBookRequest addBookRequest = new AddBookRequest("Th", "Ernest Hemingway");
        List<CoreError> errorsActual = new ArrayList<>();
        List<CoreError> errorsExpected = new ArrayList<>();
        CoreError error = new CoreError("bookTitle", "Field bookTitle is too short");
        errorsExpected.add(error);
        FieldValidatorUI subject = new FieldValidatorUI();
        errorsActual = subject.bookTitleFieldValidate(addBookRequest, errorsActual);
        Assert.assertEquals(errorsExpected, errorsActual);

    }

    @Test
    public void ShouldntValidate_space_BookTitle() {

        AddBookRequest addBookRequest = new AddBookRequest("  ", "Ernest Hemingway");
        List<CoreError> errorsActual = new ArrayList<>();
        List<CoreError> errorsExpected = new ArrayList<>();
        CoreError error = new CoreError("bookTitle", "Field bookTitle can't be Space");
        errorsExpected.add(error);
        FieldValidatorUI subject = new FieldValidatorUI();
        errorsActual = subject.bookTitleFieldValidate(addBookRequest, errorsActual);
        Assert.assertEquals(errorsExpected, errorsActual);

    }

    @Test
    public void ShouldntValidate_illegalCharacters_BookTitle() {

        AddBookRequest addBookRequest = new AddBookRequest("The Old Mann and S$ea", "Ernest Hemingway!");
        List<CoreError> errorsActual = new ArrayList<>();
        List<CoreError> errorsExpected = new ArrayList<>();
        CoreError error = new CoreError("bookTitle", "Field bookTitle contains illegal characters");
        errorsExpected.add(error);
        FieldValidatorUI subject = new FieldValidatorUI();
        errorsActual = subject.bookTitleFieldValidate(addBookRequest, errorsActual);
        Assert.assertEquals(errorsExpected, errorsActual);

    }



}