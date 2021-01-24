package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserEditRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.UserEditResponse;
import lv.javaguru.app.core.services.validators.EditUserValidator;
import lv.javaguru.app.database.UserDatabase;
import lv.javaguru.app.dependency_injection.DIComponent;
import lv.javaguru.app.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class UserEditService {
	@DIDependency
	private UserDatabase userDatabase;
	@DIDependency
	private EditUserValidator validator;


	public UserEditResponse execute (UserEditRequest request) {
		List<CodeError> responseList = validator.validate(request);

		if (!responseList.isEmpty()) {
			return new UserEditResponse(responseList);
		}
		User user = userDatabase.getUserById(request.getId());

		return new UserEditResponse(user);
	}


	public UserEditResponse execute (UserEditRequest.Name request) {
		List<CodeError> responseList = validator.validate(request);

		if (!responseList.isEmpty()) {
			return new UserEditResponse(responseList);
		}
		userDatabase.getUserById(request.getId()).setName(request.getName());

		return new UserEditResponse("Hurrah! Name has been changed");
	}


	public UserEditResponse execute (UserEditRequest.Surname request) {
		String surname = request.getSurname();

		List<CodeError> errorList = validator.validate(request);

		if (!errorList.isEmpty()) {
			return new UserEditResponse(errorList);
		}
		userDatabase.getUserById(request.getId()).setSurname(surname);

		return new UserEditResponse("Hurrah! Second name has been changed");
	}


}
