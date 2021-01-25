package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserAddRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.UserAddResponse;
import lv.javaguru.app.core.services.validators.AddUserRequestValidator;
import lv.javaguru.app.database.UserDatabase;
import lv.javaguru.app.dependency_injection.DIComponent;
import lv.javaguru.app.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class UserAddService {
	@DIDependency
	private UserDatabase userDatabase;
	@DIDependency
	private AddUserRequestValidator validator;


	public UserAddResponse execute (UserAddRequest request) {
		List<CodeError> errors = validator.validate(request);
		User user = request.getUser();

		if (!errors.isEmpty())
			return new UserAddResponse(errors);

		if (userDatabase.getAllUsers().contains(user)) {
			errors.add(new CodeError("User", "User with same credential already registered!"));

			return new UserAddResponse(errors);
		}

		userDatabase.addUser(user);

		String message = String.format("\nCongrats! %s %s, You have been registered!", user.getName(), user.getSurname());

		return new UserAddResponse(message);
	}
}
