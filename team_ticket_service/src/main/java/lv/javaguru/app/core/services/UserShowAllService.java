package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserShowAllRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.UserShowAllResponse;
import lv.javaguru.app.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserShowAllService {
	//private final Database database;
	private final UserDatabase userDatabase;

	public UserShowAllService (UserDatabase userDatabase) {
		this.userDatabase = userDatabase;
	}

	public UserShowAllResponse<?> execute (UserShowAllRequest request) {
		List<?> responseList = new ArrayList<>();


		if (!responseList.isEmpty()) {
			return new UserShowAllResponse<>(responseList);
		}

		if (request.getId() == null)
			return new UserShowAllResponse<>(userDatabase.getAllUsers());

		return new UserShowAllResponse<>(responseList);
	}

	private List<CodeError> validate (User user) {
		return new ArrayList<>();
	}
}
