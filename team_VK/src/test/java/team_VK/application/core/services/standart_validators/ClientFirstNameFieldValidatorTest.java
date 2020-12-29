package team_VK.application.core.services.standart_validators;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import team_VK.application.ApplicationContext;
import team_VK.application.core.responses.CoreError;
import team_VK.application.database.DataBaseFiller;
import team_VK.application.dependenci_injection.DIApplicationContextBuilder;

import java.io.IOException;
import java.util.List;

public class ClientFirstNameFieldValidatorTest  {


    ApplicationContext context;
    ClientFirstNameFieldValidator subject;

    @Before
    public void setup() throws IOException, ClassNotFoundException {

        context = new DIApplicationContextBuilder().build("team_VK.application");
        DataBaseFiller dataBaseFiller = context.getBean(DataBaseFiller.class);
        dataBaseFiller.fill();
        subject = context.getBean(ClientFirstNameFieldValidator.class);
    }

    @Test
    public void ShouldReturnEmptyListWhenClientFirstNameIsCorrect() {
        String clientFirstName = "FooBar";
        List<CoreError> errors = subject.validate(clientFirstName);
        Assert.assertEquals(errors.size(), 0);
    }

    @Test
    public void ShouldReturnEmptyListWhenClientFirstNameContainsDefice() {
        String clientFirstName = "Foo-bar";
        List<CoreError> errors = subject.validate(clientFirstName);
        Assert.assertEquals(errors.size(), 0);
    }

    @Test
    public void ShouldReturnEmptyListWhenClientFirstNameContainsSpace() {
        String clientFirstName = "Foo bar";
        List<CoreError> errors = subject.validate(clientFirstName);
        Assert.assertEquals(errors.size(), 0);
    }

    @Test
    public void ShouldReturnNonEmptyListWhenClientFirstNameContainsCyrillic() {
        String clientFirstName = "Фуубар";
        List<CoreError> errors = subject.validate(clientFirstName);
        Assert.assertEquals(errors.size(), 1);
    }



    @Test
    public  void ShouldReturnNonEmptyListWhenClientFirstNameContainsSpecialSymbol1() {
        String clientFirstName = "FooBar!";
        List<CoreError> errors = subject.validate(clientFirstName);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientFirstName contains illegal characters");
        Assert.assertEquals(errors.get(0).field, "clientFirstName");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenClientFirstNameContainsSpecialSymbol2() {
        String clientFirstName = "FooBar.";
        List<CoreError> errors = subject.validate(clientFirstName);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientFirstName contains illegal characters");
        Assert.assertEquals(errors.get(0).field, "clientFirstName");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenClientFirstNameContainsSpecialSymbol3() {
        String clientFirstName = "FooBar[";
        List<CoreError> errors = subject.validate(clientFirstName);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientFirstName contains illegal characters");
        Assert.assertEquals(errors.get(0).field, "clientFirstName");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenClientFirstNameContainsSpecialSymbol4() {
        String clientFirstName = "FooBar{";
        List<CoreError> errors = subject.validate(clientFirstName);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientFirstName contains illegal characters");
        Assert.assertEquals(errors.get(0).field, "clientFirstName");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenClientFirstNameHavesThreeSymbols() {
        String clientFirstName = "Fo";
        List<CoreError> errors = subject.validate(clientFirstName);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientFirstName is too short");
        Assert.assertEquals(errors.get(0).field, "clientFirstName");
    }
    @Test
    public  void ShouldReturnNonEmptyListWhenClientFirstNameIsEmpty() {
        String clientFirstName = "";
        List<CoreError> errors = subject.validate(clientFirstName);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientFirstName must be not empty");
        Assert.assertEquals(errors.get(0).field, "clientFirstName");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenClientFirstNameIsNull() {

        List<CoreError> errors = subject.validate(null);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientFirstName must be not empty");
        Assert.assertEquals(errors.get(0).field, "clientFirstName");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenClientFirstNameContainsOnlySpace() {
        String clientFirstName = " ";
        List<CoreError> errors = subject.validate(clientFirstName);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientFirstName can't Space");
        Assert.assertEquals(errors.get(0).field, "clientFirstName");
    }
}