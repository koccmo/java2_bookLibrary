package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserDeleteRequest;
import lv.javaguru.app.core.response.UserDeleteResponse;
import lv.javaguru.app.database.SqlDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDeleteService {

	@Autowired
	private SqlDatabase sqlDatabase;

	public UserDeleteResponse execute (UserDeleteRequest request) {
		List<CodeError> errors = new ArrayList<>();

		User userToDelete = sqlDatabase.getUserById(request.getId());
		String message = "";
		if (userToDelete != null)
			message = sqlDatabase.removeUser(request.getId());
		else {
			errors.add(new CodeError("Id", "No user with such Id"));
			return new UserDeleteResponse(errors);
		}


		return new UserDeleteResponse(true);
	}

}
