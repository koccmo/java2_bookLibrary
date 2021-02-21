package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.domain.Role;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserAddRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.UserAddResponse;
import lv.javaguru.app.core.services.validators.AddUserRequestValidator;
import lv.javaguru.app.database.repository.AuthoritiesRepository;
import lv.javaguru.app.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class UserAddService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AuthoritiesRepository authoritiesRepository;
	@Autowired
	private AddUserRequestValidator validator;


	public UserAddResponse execute (UserAddRequest request) {
		List<CodeError> errors = validator.validate(request);
		User user = new User(request.getName(), request.getSurname());

		if (!errors.isEmpty())
			return new UserAddResponse(errors);

		Long id = userRepository.addUser(user);
		authoritiesRepository.addAuthority(new Role(user.getUsername(), "ROLE_USER"));

		if (userRepository.getUserById(id) == null) {
			errors.add(new CodeError("User", "Haven't managed to add user!"));
			return new UserAddResponse(errors);
		}

		String message = String.format("\nCongrats! %s %s, You have been registered!", user.getUsername(), user.getPassword());

		return new UserAddResponse(message);
	}
}
