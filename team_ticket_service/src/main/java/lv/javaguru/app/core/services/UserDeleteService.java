package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.request.UserDeleteRequest;
import lv.javaguru.app.core.response.UserDeleteResponse;
import lv.javaguru.app.database.UserDatabase;
import lv.javaguru.app.dependency_injection.DIComponent;
import lv.javaguru.app.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class UserDeleteService {
	@DIDependency
	private UserDatabase userDatabase;


	public UserDeleteResponse execute (UserDeleteRequest request) {
		List<CodeError> errors = new ArrayList<>();

		boolean result = userDatabase.deleteUserById(request.getId());

		return new UserDeleteResponse(result);
	}

}
