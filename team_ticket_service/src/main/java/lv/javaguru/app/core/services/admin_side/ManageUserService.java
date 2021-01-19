package lv.javaguru.app.core.services.admin_side;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.admin.EditSurnameRequest;
import lv.javaguru.app.core.request.admin.EditUserNameRequest;
import lv.javaguru.app.core.request.admin.ManageUserRequest;
import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.admin.EditUserNameResponse;
import lv.javaguru.app.core.response.admin.EditUserSurnameResponse;
import lv.javaguru.app.core.response.admin.ManageUserResponse;
import lv.javaguru.app.database.Database;

import java.util.ArrayList;
import java.util.List;

public class ManageUserService {
	private final Database database;

	public ManageUserService (Database database) {
		this.database = database;
	}

	public ManageUserResponse execute (ManageUserRequest request) {
		List<CodeError> responseList = validateId(request.getId());

		if (!responseList.isEmpty()) {
			return new ManageUserResponse(responseList);
		}
		User user = database.getUserById(request.getId());

		return new ManageUserResponse(user);

	}


	public EditUserNameResponse execute (EditUserNameRequest request) {
		List<CodeError> responseList = validateName(request.getNewName());

		if (!responseList.isEmpty()) {
			return new EditUserNameResponse(responseList);
		}

		if (database.getUser(request.getUser()).isPresent())
			database.getUser(request.getUser()).get().setName(request.getNewName());

		return new EditUserNameResponse("Name changed!");

	}


	public EditUserSurnameResponse execute (EditSurnameRequest request) {
		List<CodeError> responseList = validateName(request.getNewSurname());

		if (!responseList.isEmpty()) {
			return new EditUserSurnameResponse(responseList);
		}

		if (database.getUser(request.getUser()).isPresent())
			database.getUser(request.getUser()).get().setSurname(request.getNewSurname());

		return new EditUserSurnameResponse("Surname changed!");

	}
	private List<CodeError> validateId (Long id) {
		List<CodeError> errorList = new ArrayList<>();

		if (!database.isContainUserWithId(id))
				errorList.add(new CodeError("id","no user with such id"));
		return errorList;
	}
	private List<CodeError> validateName (String name) {
		List<CodeError> errorList = new ArrayList<>();

		//if (!database.isContainUserWithId(id))
		//		errorList.add(new CodeError("id","no user with such id"));
		return errorList;
	}
}
