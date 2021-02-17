package lv.javaguru.app.core.services;

import lv.javaguru.app.core.request.LogOutRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.LogOutResponse;
import lv.javaguru.app.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class LogOutService {

	@Autowired
	private UserRepository userRepository;


	public LogOutResponse execute (LogOutRequest request) {
		List<CodeError> errors = new ArrayList<>();

		if (request.getUser() != null)
			errors.add(new CodeError("", "When logging out, request should contain null!"));

		if (!errors.isEmpty())
			return new LogOutResponse(errors);

		return new LogOutResponse(null, "Successfully! Logged out!");
	}
}

