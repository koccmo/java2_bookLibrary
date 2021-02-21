package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserShowAllRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.UserShowAllResponse;
import lv.javaguru.app.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class UserShowAllService {

	@Autowired
	private UserRepository userRepository;

	public UserShowAllResponse<?> execute (UserShowAllRequest request) {
		List<?> errorList = new ArrayList<>();

		if (request.getUser() != null)
			errorList = validate(request.getUser());

		if (!errorList.isEmpty()) {
			return new UserShowAllResponse<>(errorList);
		}

		if (request.getId() == null)
			return new UserShowAllResponse<>(userRepository.getAllUsers());

		return new UserShowAllResponse<>(errorList);
	}


	private List<CodeError> validate (User user) {
		List<CodeError> errorList = new ArrayList<>();

		User u = userRepository.getUserById(user.getId());

		if (u == null)
			errorList.add(new CodeError("User", "no user in database"));
		//	if (user.getPersonType() != PersonType.ADMIN)
		//		errorList.add(new CodeError("User", "User don't have required permission!"));

		return errorList;
	}
}
