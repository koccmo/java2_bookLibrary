package team_VK.application.core.services.services;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import team_VK.application.core.requests.AddClientRequest;
import team_VK.application.core.responses.CoreError;
import team_VK.application.core.services.AddClientServiceValidator;

import java.util.List;

public class AddClientServiceValidatorTest  {


    AddClientServiceValidator subject = new AddClientServiceValidator();

    @Test
    public void ShouldReturnNoErrorsWhenCorrectData (){

        AddClientRequest request = new AddClientRequest("Fuuf", "Bazz", "123456-12345");
        List<CoreError> errors = subject.validate(request);
        Assert.assertEquals(errors.size(), 0);

    }

    @Test
    public void ShouldReturnErrorWhenEmptyFirstName() {
        AddClientRequest request = new AddClientRequest("", "Bazz", "123456-12345");
        List<CoreError> errors = subject.validate(request);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).getField(), "clientFirstName");
        Assert.assertEquals(errors.get(0).getErrorMessage(), "Field clientFirstName must be not empty");
    }

    @Test
    public void ShouldReturnErrorWhenFirstNameIsSpace() {
        AddClientRequest request = new AddClientRequest("  ", "Bazz", "123456-12345");
        List<CoreError> errors = subject.validate(request);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).getField(), "clientFirstName");
        Assert.assertEquals(errors.get(0).getErrorMessage(), "Field clientFirstName can't Space");
    }

    @Test
    public void ShouldReturnErrorWhenTooShortFirstName() {
        AddClientRequest request = new AddClientRequest("Fuu", "Bazz", "123456-12345");
        List<CoreError> errors = subject.validate(request);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).getField(), "clientFirstName");
        Assert.assertEquals(errors.get(0).getErrorMessage(), "Field clientFirstName is too short");
    }

    @Test
    public void ShouldReturnErrorWhenFirstNameContainsIllegalChar1() {
        AddClientRequest request = new AddClientRequest("Fuu!", "Bazz", "123456-12345");
        List<CoreError> errors = subject.validate(request);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).getField(), "clientFirstName");
        Assert.assertEquals(errors.get(0).getErrorMessage(), "Field clientFirstName contains illegal characters");
    }

    @Test
    public void ShouldReturnErrorWhenFirstNameContainsIllegalChar2() {
        AddClientRequest request = new AddClientRequest("Fuu.", "Bazz", "123456-12345");
        List<CoreError> errors = subject.validate(request);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).getField(), "clientFirstName");
        Assert.assertEquals(errors.get(0).getErrorMessage(), "Field clientFirstName contains illegal characters");
    }

    @Test
    public void ShouldReturnErrorWhenFirstNameContainsIllegalChar3() {
        AddClientRequest request = new AddClientRequest("Fuu@", "Bazz", "123456-12345");
        List<CoreError> errors = subject.validate(request);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).getField(), "clientFirstName");
        Assert.assertEquals(errors.get(0).getErrorMessage(), "Field clientFirstName contains illegal characters");
    }

    @Test
    public void ShouldReturnErrorWhenFirstNameContainsIllegalChar4() {
        AddClientRequest request = new AddClientRequest("Fuu[", "Bazz", "123456-12345");
        List<CoreError> errors = subject.validate(request);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).getField(), "clientFirstName");
        Assert.assertEquals(errors.get(0).getErrorMessage(), "Field clientFirstName contains illegal characters");
    }

    @Test
    public void ShouldReturnErrorWhenFirstNameContainsIllegalChar5() {
        AddClientRequest request = new AddClientRequest("Fuu'", "Bazz", "123456-12345");
        List<CoreError> errors = subject.validate(request);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).getField(), "clientFirstName");
        Assert.assertEquals(errors.get(0).getErrorMessage(), "Field clientFirstName contains illegal characters");
    }

    @Test
    public void ShouldReturnErrorWhenFirstNameContainsIllegalChar6() {
        AddClientRequest request = new AddClientRequest("Fuu{", "Bazz", "123456-12345");
        List<CoreError> errors = subject.validate(request);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).getField(), "clientFirstName");
        Assert.assertEquals(errors.get(0).getErrorMessage(), "Field clientFirstName contains illegal characters");
    }





    @Test
    public void ShouldReturnErrorWhenEmptyLastName() {
        AddClientRequest request = new AddClientRequest("Fuuf", "", "123456-12345");
        List<CoreError> errors = subject.validate(request);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).getField(), "clientLastName");
        Assert.assertEquals(errors.get(0).getErrorMessage(), "Field clientLastName must be not empty");
    }

    @Test
    public void ShouldReturnErrorWhenLastNameIsSpace() {
        AddClientRequest request = new AddClientRequest("Fuuf", "  ", "123456-12345");
        List<CoreError> errors = subject.validate(request);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).getField(), "clientLastName");
        Assert.assertEquals(errors.get(0).getErrorMessage(), "Field clientLastName can't be Space");
    }

    @Test
    public void ShouldReturnErrorWhenTooShortLastName() {
        AddClientRequest request = new AddClientRequest("Fuuf", "Baz", "123456-12345");
        List<CoreError> errors = subject.validate(request);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).getField(), "clientLastName");
        Assert.assertEquals(errors.get(0).getErrorMessage(), "Field clientLastName is too short");
    }

    @Test
    public void ShouldReturnErrorWhenLastNameContainsIllegalChar1() {
        AddClientRequest request = new AddClientRequest("Fuuf", "Baz!", "123456-12345");
        List<CoreError> errors = subject.validate(request);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).getField(), "clientLastName");
        Assert.assertEquals(errors.get(0).getErrorMessage(), "Field clientLastName contains illegal characters");
    }

    @Test
    public void ShouldReturnErrorWhenLastNameContainsIllegalChar2() {
        AddClientRequest request = new AddClientRequest("Fuuf", "Bazz.", "123456-12345");
        List<CoreError> errors = subject.validate(request);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).getField(), "clientLastName");
        Assert.assertEquals(errors.get(0).getErrorMessage(), "Field clientLastName contains illegal characters");
    }

    @Test
    public void ShouldReturnErrorWhenLastNameContainsIllegalChar3() {
        AddClientRequest request = new AddClientRequest("Fuuf", "Baz@", "123456-12345");
        List<CoreError> errors = subject.validate(request);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).getField(), "clientLastName");
        Assert.assertEquals(errors.get(0).getErrorMessage(), "Field clientLastName contains illegal characters");
    }

    @Test
    public void ShouldReturnErrorWhenLastNameContainsIllegalChar4() {
        AddClientRequest request = new AddClientRequest("Fuuf", "Bazz[", "123456-12345");
        List<CoreError> errors = subject.validate(request);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).getField(), "clientLastName");
        Assert.assertEquals(errors.get(0).getErrorMessage(), "Field clientLastName contains illegal characters");
    }

    @Test
    public void ShouldReturnErrorWhenLastNameContainsIllegalChar5() {
        AddClientRequest request = new AddClientRequest("Fuuf", "Bazz'", "123456-12345");
        List<CoreError> errors = subject.validate(request);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).getField(), "clientLastName");
        Assert.assertEquals(errors.get(0).getErrorMessage(), "Field clientLastName contains illegal characters");
    }

    @Test
    public void ShouldReturnErrorWhenLastNameContainsIllegalChar6() {
        AddClientRequest request = new AddClientRequest("Fuuf", "Bazz{", "123456-12345");
        List<CoreError> errors = subject.validate(request);
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0).getField(), "clientLastName");
        Assert.assertEquals(errors.get(0).getErrorMessage(), "Field clientLastName contains illegal characters");
    }


}