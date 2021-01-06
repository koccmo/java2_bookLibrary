package team_VK.application.core.services.standart_validators;

import org.junit.Assert;
import org.junit.Test;
import team_VK.application.core.responses.CoreError;

import java.util.List;

public class ClientLastNameFieldValidatorTest {

    ClientLastNameFieldValidator subject = new ClientLastNameFieldValidator();

    @Test
    public void ShouldReturnEmptyListWhenClientFirstNameIsCorrect() {
        String clientLastName = "FooBar";
        List<CoreError> errors = subject.validate(clientLastName);
        Assert.assertEquals(errors.size(), 0);
    }

    @Test
    public void ShouldReturnEmptyListWhenClientFirstNameContainsDefice() {
        String clientLastName = "Foo-bar";
        List<CoreError> errors = subject.validate(clientLastName);
        Assert.assertEquals(errors.size(), 0);
    }

    @Test
    public void ShouldReturnEmptyListWhenClientFirstNameContainsSpace() {
        String clientLastName = "Foo bar";
        List<CoreError> errors = subject.validate(clientLastName);
        Assert.assertEquals(errors.size(), 0);
    }

    @Test
    public void ShouldReturnNonEmptyListWhenClientFirstNameContainsCyrillic() {
        String clientLastName = "Фуубар";
        List<CoreError> errors = subject.validate(clientLastName);
        Assert.assertEquals(errors.size(), 1);
    }



    @Test
    public  void ShouldReturnNonEmptyListWhenClientFirstNameContainsSpecialSymbol1() {
        String clientLastName = "FooBar!";
        List<CoreError> errors = subject.validate(clientLastName);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientLastName contains illegal characters");
        Assert.assertEquals(errors.get(0).field, "clientLastName");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenClientFirstNameContainsSpecialSymbol2() {
        String clientLastName = "FooBar.";
        List<CoreError> errors = subject.validate(clientLastName);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientLastName contains illegal characters");
        Assert.assertEquals(errors.get(0).field, "clientLastName");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenClientFirstNameContainsSpecialSymbol3() {
        String clientLastName = "FooBar[";
        List<CoreError> errors = subject.validate(clientLastName);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientLastName contains illegal characters");
        Assert.assertEquals(errors.get(0).field, "clientLastName");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenClientFirstNameContainsSpecialSymbol4() {
        String clientLastName = "FooBar{";
        List<CoreError> errors = subject.validate(clientLastName);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientLastName contains illegal characters");
        Assert.assertEquals(errors.get(0).field, "clientLastName");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenClientFirstNameHavesThreeSymbols() {
        String clientLastName = "Fo";
        List<CoreError> errors = subject.validate(clientLastName);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientLastName is too short");
        Assert.assertEquals(errors.get(0).field, "clientLastName");
    }
    @Test
    public  void ShouldReturnNonEmptyListWhenClientFirstNameIsEmpty() {
        String clientLastName = "";
        List<CoreError> errors = subject.validate(clientLastName);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientLastName must be not empty");
        Assert.assertEquals(errors.get(0).field, "clientLastName");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenClientFirstNameIsNull() {

        List<CoreError> errors = subject.validate(null);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientLastName must be not empty");
        Assert.assertEquals(errors.get(0).field, "clientLastName");
    }

    @Test
    public  void ShouldReturnNonEmptyListWhenClientFirstNameContainsOnlySpace() {
        String clientLastName = " ";
        List<CoreError> errors = subject.validate(clientLastName);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).errorMessage , "Field clientLastName can't be Space");
        Assert.assertEquals(errors.get(0).field, "clientLastName");
    }
}