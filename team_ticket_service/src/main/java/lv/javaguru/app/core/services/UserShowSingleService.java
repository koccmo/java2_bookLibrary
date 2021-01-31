package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserShowSingleRequest;
import lv.javaguru.app.core.response.UserShowSingleResponse;
import lv.javaguru.app.database.SqlDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserShowSingleService {

	@Autowired
	private SqlDatabase sqlDatabase;

	public UserShowSingleResponse execute (UserShowSingleRequest request) {

		User user = sqlDatabase.getUserById(request.getId());

		return new UserShowSingleResponse(user);

	}

}
