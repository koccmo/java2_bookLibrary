package lv.javaguru.app.core.services;

import lv.javaguru.app.core.request.UserAddRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.RegistrationResponse;
import lv.javaguru.app.core.services.validators.AddUserRequestValidator;
import lv.javaguru.app.database.UserDatabase;

import java.util.List;

public class UserAddService {
	private final UserDatabase userDatabase;
	private final AddUserRequestValidator validator;


	public UserAddService (UserDatabase userDatabase, AddUserRequestValidator validator) {
		this.userDatabase = userDatabase;
		this.validator = validator;
	}


	public RegistrationResponse execute (UserAddRequest request) {
		List<CodeError> errors = validator.validate(request);

		if (!errors.isEmpty())
			return new RegistrationResponse(errors);

		userDatabase.addUser(request.getUser());

		String message = "Hooray! You have been registered!";

		return new RegistrationResponse(message);
	}
}
