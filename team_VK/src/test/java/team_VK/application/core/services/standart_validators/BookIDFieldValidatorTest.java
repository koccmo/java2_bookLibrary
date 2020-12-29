package team_VK.application.core.services.standart_validators;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import team_VK.application.ApplicationContext;
import team_VK.application.core.responses.CoreError;
import team_VK.application.database.DataBaseFiller;
import team_VK.application.dependenci_injection.DIApplicationContextBuilder;

import java.io.IOException;
import java.util.List;

public class BookIDFieldValidatorTest {

    ApplicationContext context;
    BookIDFieldValidator subject;

    @Before
    public void setup() throws IOException, ClassNotFoundException {

        context = new DIApplicationContextBuilder().build("team_VK.application");
        DataBaseFiller dataBaseFiller = context.getBean(DataBaseFiller.class);
        dataBaseFiller.fill();
        subject = context.getBean(BookIDFieldValidator.class);
    }

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