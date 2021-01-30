package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.request.UserDeleteRequest;
import lv.javaguru.app.core.response.UserDeleteResponse;
import lv.javaguru.app.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDeleteService {

	@Autowired
	private Database database;


	public UserDeleteResponse execute (UserDeleteRequest request) {
		List<CodeError> errors = new ArrayList<>();

		boolean result = database.deleteUserById(request.getId());

		return new UserDeleteResponse(result);
	}

}
