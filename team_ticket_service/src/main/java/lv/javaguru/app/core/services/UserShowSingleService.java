package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserShowSingleRequest;
import lv.javaguru.app.core.response.UserShowSingleResponse;
import lv.javaguru.app.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserShowSingleService {
	@Autowired
	private Database database;


	public UserShowSingleResponse execute (UserShowSingleRequest request) {

		User user = database.getUserById(request.getId());

		return new UserShowSingleResponse(user);

	}

}
