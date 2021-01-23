package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserShowAllRequest;
import lv.javaguru.app.core.request.UserShowSingleRequest;
import lv.javaguru.app.core.response.UserShowSingleResponse;
import lv.javaguru.app.database.UserDatabase;

import java.util.Optional;

public class UserShowSingleService {
	private final UserDatabase userDatabase;

	public UserShowSingleService (UserDatabase userDatabase) {
		this.userDatabase = userDatabase;
	}

	public UserShowSingleResponse execute (UserShowSingleRequest request) {

		User user = userDatabase.getUserById(request.getId());

		return new UserShowSingleResponse(user);

	}

}
