package lv.javaguru.app.core.services;

import lv.javaguru.app.console_ui.modes.AdminMode;
import lv.javaguru.app.console_ui.modes.UserMode;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.LogInRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.LogInResponse;
import lv.javaguru.app.core.services.validators.LoginRequestValidator;
import lv.javaguru.app.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class LogInService {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LoginRequestValidator validator;


	public LogInResponse execute (LogInRequest request) {
		List<CodeError> errors = validator.validate(request);

		if (!errors.isEmpty())
			return new LogInResponse(errors);

		User u = userRepository.getUserByNameAndSurname(request.getName(), request.getSurname());
		if (u == null) {
			errors.add(new CodeError("database", "No such user"));
			return new LogInResponse(errors);
		}
		else {
			//LogInResponse logInResponse = (u.getPersonType() == PersonType.ADMIN) ?
			LogInResponse logInResponse = (true) ?
					new LogInResponse(new AdminMode(applicationContext)) :
					new LogInResponse(new UserMode(applicationContext));

			logInResponse.setCurrUser(u);
			logInResponse.setMessage("Successfully logged in!");

			return logInResponse;
		}


	}


}
