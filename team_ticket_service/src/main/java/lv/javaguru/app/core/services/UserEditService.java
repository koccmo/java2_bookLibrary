package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserEditRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.admin.EditUserResponse;
import lv.javaguru.app.core.services.validators.EditUserValidator;
import lv.javaguru.app.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserEditService {
	private final UserDatabase userDatabase;
	private final EditUserValidator validator;

	public UserEditService (UserDatabase userDatabase, EditUserValidator validator) {
		this.userDatabase = userDatabase;
		this.validator = validator;
	}

	public EditUserResponse execute (UserEditRequest request) {
		List<CodeError> responseList = validateId(request.getId());

		if (!responseList.isEmpty()) {
			return new EditUserResponse(responseList);
		}
		User user = userDatabase.getUserById(request.getId());

		return new EditUserResponse(user);
	}


	public EditUserResponse execute (UserEditRequest.Name request) {
		List<CodeError> responseList = validator.validateName(request.getName());

		if (!responseList.isEmpty()) {
			return new EditUserResponse(responseList);
		}
		userDatabase.getUserById(request.getId()).setName(request.getName());

		return new EditUserResponse("Hoorray! name changed");
	}




	public EditUserResponse execute (UserEditRequest.Surname request) {
		String surname = request.getSurname();

		List<CodeError> errorList = validator.validateName(surname);

		if (!errorList.isEmpty()) {
			return new EditUserResponse(errorList);
		}
		userDatabase.getUserById(request.getId()).setSurname(surname);

		return new EditUserResponse("Surname updated!");
	}


	private List<CodeError> validateId (Long id) {
		List<CodeError> errorList = new ArrayList<>();

		if (!userDatabase.containsUser(id))
			errorList.add(new CodeError("id", "no user with such id"));
		return errorList;
	}

}