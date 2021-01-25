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
	private User user;

	@Before
	public void setUp () {
		validator = new LoginRequestValidator();
		user = new User();
	}

	@Test
	public void noUserNameTest () {
		user.setSurname("aleksejevs");

		request = new LogInRequest(user);

		List<CodeError> errors = validator.validate(request);

		Assert.assertEquals("Failed!", "[CodeError{field='User name', message='Null or empty string!'}]", errors.toString());
	}

	@Test
	public void UserNameContainsDigitsTest () {
		user.setName("sergejs342324");
		user.setSurname("aleksejevs");

		request = new LogInRequest(user);

		List<CodeError> errors = validator.validate(request);

		Assert.assertEquals("Failed!", "[CodeError{field='User name', message='User name shouldn't contain digits!'}]", errors.toString());
	}

	@Test
	public void UserNameContainsSymbolsTest () {
		user.setName("sergejs$#@$@#");
		user.setSurname("aleksejevs");

		request = new LogInRequest(user);

		List<CodeError> errors = validator.validate(request);

		Assert.assertEquals("Failed!", "[CodeError{field='User name', message='User name shouldn't contain symbols!'}]", errors.toString());
	}

	@Test
	public void noUserSurnameTest () {
		user.setName("Sergejs");

		request = new LogInRequest(user);

		List<CodeError> errors = validator.validate(request);

		Assert.assertEquals("Failed!", "[CodeError{field='User surname', message='Null or empty string!'}]", errors.toString());
	}

	@Test
	public void UserSurnameContainsDigitsTest () {
		user.setName("sergejs");
		user.setSurname("aleksejevs342324");

		request = new LogInRequest(user);

		List<CodeError> errors = validator.validate(request);

		Assert.assertEquals("Failed!", "[CodeError{field='User surname', message='User surname shouldn't contain digits!'}]", errors.toString());
	}

	@Test
	public void LoginSurnameContainsSymbolsTest () {
		user.setName("sergejs");
		user.setSurname("aleksejevs$#@$@#");

		request = new LogInRequest(user);

		List<CodeError> errors = validator.validate(request);

		Assert.assertEquals("Failed!", "[CodeError{field='User surname', message='User surname shouldn't contain symbols!'}]", errors.toString());
	}

}