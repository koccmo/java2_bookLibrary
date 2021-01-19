package lv.javaguru.app.core.services;

import lv.javaguru.app.console_ui.Action;
import lv.javaguru.app.console_ui.admin_side.AdminMode;
import lv.javaguru.app.console_ui.user_side.UserMode;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.LogInRequest;
import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.LogInResponse;
import lv.javaguru.app.core.services.validators.LoginRequestValidator;
import lv.javaguru.app.database.Database;

import java.util.List;
import java.util.Optional;

public class LogInService {

	private final Database database;
	private final LoginRequestValidator validator;

	public LogInService (Database database, LoginRequestValidator validator) {
		this.database = database;
		this.validator = validator;
	}

	public LogInResponse execute (LogInRequest request) {
		List<CodeError> errors = validator.validate(request);

		if (!errors.isEmpty())
			return new LogInResponse(errors);


		Optional<User> optionalUser = database.getUser(request.getUser());
		User user;
		if (optionalUser.isPresent()) {
			user = optionalUser.get();
			database.setCurrentUser(user);
		}
		else {
			errors.add(new CodeError("", "No such user"));
			return new LogInResponse(errors);
		}


		LogInResponse logInResponse = (user.getPersonType() == PersonType.ADMIN) ?
				new LogInResponse(new AdminMode(database)) :
				new LogInResponse(new UserMode(database));

		logInResponse.setCurrUser(user);

		return logInResponse;


	}


}
