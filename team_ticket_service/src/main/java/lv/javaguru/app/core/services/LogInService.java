package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.Person;
import lv.javaguru.app.core.request.LogInRequest;
import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.LogInResponse;
import lv.javaguru.app.core.validators.LoginRequestValidator;
import lv.javaguru.app.database.Database;

import java.util.List;

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

		else {
			Person result = database.getPerson(request.getUser());
			if (result != null)
				database.setCurrentPerson(result);

			return new LogInResponse(database.getCurrentPerson());
		}
	}


}
