package lv.javaguru.app.core.services;

import lv.javaguru.app.core.request.LogOutRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.LogOutResponse;
import lv.javaguru.app.database.UserDatabase;
import lv.javaguru.app.dependency_injection.DIComponent;
import lv.javaguru.app.dependency_injection.DIDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LogOutService {

	@Autowired
	private UserDatabase userDatabase;


	public LogOutResponse execute (LogOutRequest request) {
		List<CodeError> errors = new ArrayList<>();

		if (request.getPerson() != null)
			errors.add(new CodeError("", "When logging out, request should contain null!"));

		if (!errors.isEmpty())
			return new LogOutResponse(errors);

		else {
			userDatabase.setCurrentUser(null);

			return new LogOutResponse(null, "Successfully! Logged out!");
		}
	}
}
