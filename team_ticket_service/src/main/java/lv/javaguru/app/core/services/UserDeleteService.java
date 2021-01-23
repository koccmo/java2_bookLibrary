package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.request.DeleteFlightRequest;
import lv.javaguru.app.core.request.UserDeleteRequest;
import lv.javaguru.app.core.response.DeleteFlightResponse;
import lv.javaguru.app.core.response.UserDeleteResponse;
import lv.javaguru.app.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserDeleteService {
	private final UserDatabase userDatabase;

	public UserDeleteService (UserDatabase userDatabase) {
		this.userDatabase = userDatabase;
	}

	public UserDeleteResponse execute (UserDeleteRequest request) {
		List<CodeError> errors = new ArrayList<>();

		boolean result = userDatabase.deleteUserById(request.getId());

		return new UserDeleteResponse(result);
	}

}
