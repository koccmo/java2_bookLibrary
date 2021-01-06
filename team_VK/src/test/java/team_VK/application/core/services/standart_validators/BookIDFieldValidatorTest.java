package team_VK.application.core.services.standart_validators;

import org.junit.Assert;
import org.junit.Test;
import team_VK.application.core.responses.CoreError;

import java.util.List;

public class BookIDFieldValidatorTest {

    BookIDFieldValidator subject = new BookIDFieldValidator();

    @Test
    public void ShouldReturnEmptyListWhenIdHavesOneNumericSymbol() {
        String bookID = "5";
        List<CoreError> errors = subject.validate(bookID);
        Assert.assertEquals(errors.size(), 0);
    }

    @Test
    public void ShouldReturnEmptyListWhenIdHavesTwoNumericSymbol() {
        String bookID = "51";
        List<CoreError> errors = subject.validate(bookID);
        Assert.assertEquals(errors.size(), 0);
    }

    @Test
    public void ShouldReturnEmptyListWhenIdHavesSixNumericSymbol() {
        String bookID = "515151";
        List<CoreError> errors = subject.validate(bookID);
        Assert.assertEquals(errors.size(), 0);
    }



    @Test
    public  void ShouldReturnNonEmptyListWhenIdIsSpecialSymbol() {
        String bookID = "!";
        List<CoreError> errors = subject.validate(bookID);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field bookID must be numeric");
        Assert.assertEquals(errors.get(0).field, "bookID");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenIdHavesIllegalSymbolInTheMiddle() {
        String bookID = "2!1";
        List<CoreError> errors = subject.validate(bookID);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field bookID must be numeric");
        Assert.assertEquals(errors.get(0).field, "bookID");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenIdIsEmpty() {
        String bookID = "";
        List<CoreError> errors = subject.validate(bookID);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field bookID must be numeric");
        Assert.assertEquals(errors.get(0).field, "bookID");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenIdIsString() {
        String bookID = "Buz";
        List<CoreError> errors = subject.validate(bookID);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field bookID must be numeric");
        Assert.assertEquals(errors.get(0).field, "bookID");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenIdIsNegative() {
        String bookID = "-1";
        List<CoreError> errors = subject.validate(bookID);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field bookID must be numeric");
        Assert.assertEquals(errors.get(0).field, "bookID");
    }

}