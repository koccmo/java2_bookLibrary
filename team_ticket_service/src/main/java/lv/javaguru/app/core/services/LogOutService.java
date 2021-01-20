package lv.javaguru.app.core.services;

import lv.javaguru.app.core.request.LogOutRequest;
import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.LogOutResponse;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class LogOutService {

	private final UserDatabase userDatabase;

	public LogOutService (UserDatabase userDatabase) {
		this.userDatabase = userDatabase;

	}

	public LogOutResponse execute (LogOutRequest request) {
		List<CodeError> errors = new ArrayList<>();

		if (request.getPerson() != null)
			errors.add(new CodeError("", "When logging out, request should contain null!"));

		if (!errors.isEmpty())
			return new LogOutResponse(errors);

		else {
			userDatabase.setCurrentUser(null);

			return new LogOutResponse(null, "Successfully! Logged out!");
		}
	}
}
