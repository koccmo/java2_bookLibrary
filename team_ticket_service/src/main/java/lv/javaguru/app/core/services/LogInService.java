package lv.javaguru.app.core.services;

import lv.javaguru.app.ApplicationContext;
import lv.javaguru.app.console_ui.modes.AdminMode;
import lv.javaguru.app.console_ui.modes.UserMode;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.LogInRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.LogInResponse;
import lv.javaguru.app.core.services.validators.LoginRequestValidator;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.UserDatabase;

import java.util.List;
import java.util.Optional;

public class LogInService {
	private final Database database;
	private final UserDatabase userDatabase;
	private final LoginRequestValidator validator;

	public LogInService (UserDatabase userDatabase, Database database, LoginRequestValidator validator) {
		this.userDatabase = userDatabase;
		this.database = database;
		this.validator = validator;
	}

	public LogInResponse execute (LogInRequest request) {
		List<CodeError> errors = validator.validate(request);

		if (!errors.isEmpty())
			return new LogInResponse(errors);


		Optional<User> optionalUser = userDatabase.getUser(request.getUser());
		User user;
		if (optionalUser.isPresent()) {
			user = optionalUser.get();
			userDatabase.setCurrentUser(user);
		}
		else {
			errors.add(new CodeError("database", "No such user"));
			return new LogInResponse(errors);
		}


		LogInResponse logInResponse = (user.getPersonType() == PersonType.ADMIN) ?
				new LogInResponse(new AdminMode()) :
				new LogInResponse(new UserMode());

		logInResponse.setCurrUser(user);
		logInResponse.setMessage("Successfully logged in!");

		return logInResponse;


	}


}
