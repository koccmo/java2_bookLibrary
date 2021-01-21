package lv.javaguru.app.core.services;

import lv.javaguru.app.core.request.RegistrationRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.RegistrationResponse;
import lv.javaguru.app.core.services.validators.RegisterRequestValidator;
import lv.javaguru.app.database.UserDatabase;

import java.util.List;

public class RegisterService {
	private final UserDatabase userDatabase;
	private final RegisterRequestValidator validator;


	public RegisterService (UserDatabase userDatabase, RegisterRequestValidator validator) {
		this.userDatabase = userDatabase;
		this.validator = validator;
	}


	public RegistrationResponse execute (RegistrationRequest request) {
		List<CodeError> errors = validator.validate(request);

		if (!errors.isEmpty())
			return new RegistrationResponse(errors);

		userDatabase.addUser(request.getUser());

		String message = "Hooray! You have been registered!";

		return new RegistrationResponse(message);
	}
}
