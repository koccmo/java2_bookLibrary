package lv.javaguru.app.core.services.validators;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.LogInRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class LoginRequestValidatorTest {
	private LoginRequestValidator validator;
	private LogInRequest request;

	@Before
	public void setUp () {
		validator = new LoginRequestValidator();
	}

	@Test
	public void noUserNameTest () {
		request = new LogInRequest("", "aleksejevs");

		List<CodeError> errors = validator.validate(request);

		Assert.assertEquals("Failed!", "[CodeError{field='User name', message='Null or empty string!'}]", errors.toString());
	}

	@Test
	public void UserNameContainsDigitsTest () {
		request = new LogInRequest("sergejs342324", "aleksejevs");

		List<CodeError> errors = validator.validate(request);

		Assert.assertEquals("Failed!", "[CodeError{field='User name', message='User name shouldn't contain digits!'}]", errors.toString());
	}

	@Test
	public void UserNameContainsSymbolsTest () {
		request = new LogInRequest("sergejs$#@$@#", "aleksejevs");

		List<CodeError> errors = validator.validate(request);

		Assert.assertEquals("Failed!", "[CodeError{field='User name', message='User name shouldn't contain symbols!'}]", errors.toString());
	}

	@Test
	public void noUserSurnameTest () {
		request = new LogInRequest("Sergejs", "");

		List<CodeError> errors = validator.validate(request);

		Assert.assertEquals("Failed!", "[CodeError{field='User surname', message='Null or empty string!'}]", errors.toString());
	}

	@Test
	public void UserSurnameContainsDigitsTest () {
		request = new LogInRequest("Sergejs", "aleksejevs342324");

		List<CodeError> errors = validator.validate(request);

		Assert.assertEquals("Failed!", "[CodeError{field='User surname', message='User surname shouldn't contain digits!'}]", errors.toString());
	}

	@Test
	public void LoginSurnameContainsSymbolsTest () {
		request = new LogInRequest("Sergejs", "aleksejevs$#@$@#");

		List<CodeError> errors = validator.validate(request);

		Assert.assertEquals("Failed!", "[CodeError{field='User surname', message='User surname shouldn't contain symbols!'}]", errors.toString());
	}

}