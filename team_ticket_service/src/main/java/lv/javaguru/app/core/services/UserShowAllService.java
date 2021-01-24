package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserShowAllRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.UserShowAllResponse;
import lv.javaguru.app.database.UserDatabase;
import lv.javaguru.app.dependency_injection.DIComponent;
import lv.javaguru.app.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class UserShowAllService {
	@DIDependency
	private UserDatabase userDatabase;


	public UserShowAllResponse<?> execute (UserShowAllRequest request) {
		List<?> errorList = validate(request.getUser());


		if (!errorList.isEmpty()) {
			return new UserShowAllResponse<>(errorList);
		}

		if (request.getId() == null)
			return new UserShowAllResponse<>(userDatabase.getAllUsers());

		return new UserShowAllResponse<>(errorList);
	}


	private List<CodeError> validate (User user) {
		List<CodeError> errorList = new ArrayList<>();

		if (!userDatabase.containsUser(user.getId()))
			errorList.add(new CodeError("User", "no user in database"));
		if (user.getPersonType() != PersonType.ADMIN)
			errorList.add(new CodeError("User", "User don't have required permission!"));

		return errorList;
	}
}
