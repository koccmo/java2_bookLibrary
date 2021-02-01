package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserAddRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.UserAddResponse;
import lv.javaguru.app.core.services.validators.AddUserRequestValidator;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.SqlDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserAddService {
	@Autowired
	private Database database;
	@Autowired
	private SqlDatabase sqlDatabase;
	@Autowired
	private AddUserRequestValidator validator;


	public UserAddResponse execute (UserAddRequest request) {
		List<CodeError> errors = validator.validate(request);
		User user = request.getUser();

		if (!errors.isEmpty())
			return new UserAddResponse(errors);

		if (!sqlDatabase.addUser(user)) {
			errors.add(new CodeError("User", "Haven't managed to add user!"));
			return new UserAddResponse(errors);
		}

		String message = String.format("\nCongrats! %s %s, You have been registered!", user.getName(), user.getSurname());

		return new UserAddResponse(message);
	}
}
