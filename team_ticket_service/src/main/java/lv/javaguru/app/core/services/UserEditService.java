package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserEditRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.request.user_update.UpdateUserUsername;
import lv.javaguru.app.core.response.UserEditResponse;
import lv.javaguru.app.core.services.validators.EditUserRequestValidator;
import lv.javaguru.app.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UserEditService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EditUserRequestValidator validator;


	public UserEditResponse execute (UserEditRequest request) {
		List<CodeError> errorList = validator.validate(request);

		if (!errorList.isEmpty()) {
			return new UserEditResponse(errorList);
		}

		User user = userRepository.getUserById(request.getId());

		if (user == null) {
			errorList.add(new CodeError("ID", "No user with such ID!"));
			return new UserEditResponse(errorList);
		}

		return new UserEditResponse(user);
	}

	public UserEditResponse execute (UpdateUserUsername request) {
		String name = request.getUserNewUsername();

		List<CodeError> responseList = validator.validateName(name);

		if (!responseList.isEmpty()) {
			return new UserEditResponse(responseList);
		}
		userRepository.updateUserNameByUserId(request.getId(), name);

		return new UserEditResponse("Hurrah! Name has been changed");
	}

	public UserEditResponse executeNameUpdate (UserEditRequest request) {
		String name = request.getNewValue();

		List<CodeError> responseList = validator.validateName(name);

		if (!responseList.isEmpty()) {
			return new UserEditResponse(responseList);
		}
		userRepository.updateUserNameByUserId(request.getId(), name);

		return new UserEditResponse("Hurrah! Name has been changed");
	}


	public UserEditResponse executeSurnameUpdate (UserEditRequest request) {
		String surname = request.getNewValue();

		List<CodeError> errorList = validator.validateSurname(surname);

		if (!errorList.isEmpty()) {
			return new UserEditResponse(errorList);
		}
		userRepository.updateUserSurnameById(request.getId(), surname);

		return new UserEditResponse("Hurrah! Surname has been changed");
	}


}
