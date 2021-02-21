package lv.javaguru.app.core.services.validators;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserAddRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class AddUserRequestValidatorTest {

	private AddUserRequestValidator validator;
	private UserAddRequest request;
	//private User user;

	@Before
	public void setUp () {
		validator = new AddUserRequestValidator();
		//user = new User();
	}

	@Test
	public void noUserNameTest () {
		//user.setSurname("aleksejevs");

		request = new UserAddRequest("", "aleksejevs");

		List<CodeError> errors = validator.validate(request);

		Assert.assertEquals("Failed!", "[CodeError{field='First name', message='Null or empty string!'}]", errors.toString());
	}

	@Test
	public void UserNameContainsDigitsTest () {
		//user.setName("sergejs342324");
		//user.setSurname("aleksejevs");

		request = new UserAddRequest("sergejs342324", "aleksejevs");

		List<CodeError> errors = validator.validate(request);

		Assert.assertEquals("Failed!", "[CodeError{field='First name', message='First name shouldn't contain digits!'}]", errors.toString());
	}

	@Test
	public void UserNameContainsSymbolsTest () {
		//user.setName("sergejs$#@$@#");
		//user.setSurname("aleksejevs");

		request = new UserAddRequest("sergejs$#@$@#", "aleksejevs");

		List<CodeError> errors = validator.validate(request);

		Assert.assertEquals("Failed!", "[CodeError{field='First name', message='First name shouldn't contain symbols!'}]", errors.toString());
	}

	@Test
	public void noUserSurnameTest () {
		//user.setName("Sergejs");

		request = new UserAddRequest("Sergejs", null);

		List<CodeError> errors = validator.validate(request);

		Assert.assertEquals("Failed!", "[CodeError{field='Second name', message='Null or empty string!'}]", errors.toString());
	}

	@Test
	public void UserSurnameContainsDigitsTest () {
		//user.setName("sergejs");
		//user.setSurname("aleksejevs342324");

		request = new UserAddRequest("sergejs", "aleksejevs342324");

		List<CodeError> errors = validator.validate(request);

		Assert.assertEquals("Failed!", "[CodeError{field='Second name', message='Second name shouldn't contain digits!'}]", errors.toString());
	}

	@Test
	public void UserSurnameContainsSymbolsTest () {
		//user.setName("sergejs");
		//user.setSurname("aleksejevs$#@$@#");

		request = new UserAddRequest("sergejs", "aleksejevs$#@$@#");

		List<CodeError> errors = validator.validate(request);

		Assert.assertEquals("Failed!", "[CodeError{field='Second name', message='Second name shouldn't contain symbols!'}]", errors.toString());
	}

}