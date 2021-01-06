package team_VK.application.core.services.standart_validators;

import org.junit.Assert;
import org.junit.Test;
import team_VK.application.core.requests.AddBookRequest;
import team_VK.application.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class TitleFieldValidatorTest {

    private TitleFieldValidator subject = new TitleFieldValidator();

    @Test
    public void ShouldValidateCorrectBookTitle() {
// positive functional test

        AddBookRequest addBookRequest = new AddBookRequest("The Old Mann and Sea", "Ernest Hemingway", 10);
        List<CoreError> errors = subject.validate(addBookRequest.bookTitle);
        Assert.assertEquals(0, errors.size());
    }

    @Test
    public void ShouldntValidate_too_short_BookTitle() {
// negative. bookTitle contains less then 3 letter // boundary test

        AddBookRequest addBookRequest = new AddBookRequest("Th", "Ernest Hemingway", 10);

        List<CoreError> errorsExpected = new ArrayList<>();
        CoreError error = new CoreError("bookTitle", "Field bookTitle is too short");
        errorsExpected.add(error);

        List<CoreError> errorsActual = subject.validate(addBookRequest.bookTitle);
        Assert.assertEquals(errorsExpected, errorsActual);
    }

    @Test
    public void ShouldntValidate_space_BookTitle() {
        // negative. bookTitle contains only Spaces // boundary test

        AddBookRequest addBookRequest = new AddBookRequest("  ", "Ernest Hemingway", 10);

        List<CoreError> errorsExpected = new ArrayList<>();
        CoreError error = new CoreError("bookTitle", "Field bookTitle can't be Space");
        errorsExpected.add(error);

        List<CoreError> errorsActual = subject.validate(addBookRequest.bookTitle);
        Assert.assertEquals(errorsExpected, errorsActual);
    }

    @Test
    public void ShouldntValidate_illegalCharacters_BookTitle() {
// negative. bookAuthor contains illegal character with code 35 // boundary test

        AddBookRequest addBookRequest = new AddBookRequest("The Old Mann and S#ea", "Ernest Hemingway", 10);

        List<CoreError> errorsExpected = new ArrayList<>();
        CoreError error = new CoreError("bookTitle", "Field bookTitle contains illegal characters");
        errorsExpected.add(error);

        List<CoreError> errorsActual = subject.validate(addBookRequest.bookTitle);
        Assert.assertEquals(errorsExpected, errorsActual);
    }


}