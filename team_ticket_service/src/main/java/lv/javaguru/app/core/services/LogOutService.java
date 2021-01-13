package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.Person;
import lv.javaguru.app.core.request.LogOutRequest;
import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.LogInResponse;
import lv.javaguru.app.core.response.LogOutResponse;
import lv.javaguru.app.database.Database;

import java.util.ArrayList;
import java.util.List;

public class LogOutService {

	private final Database database;

	public LogOutService (Database database) {
		this.database = database;
	}

	public LogOutResponse execute (LogOutRequest request) {
		List<CodeError> errors = new ArrayList<>();

		if (request.getPerson() != null)
			errors.add(new CodeError("", "When logging out, request should contain null!"));

		if (!errors.isEmpty())
			return new LogOutResponse(errors);

		else {
			database.setCurrentPerson(null);

			return new LogOutResponse(null, "Successfully! Logged out!");
		}
	}
}
