package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserShowAllRequest;
import lv.javaguru.app.core.request.UserShowSingleRequest;
import lv.javaguru.app.core.response.UserShowSingleResponse;
import lv.javaguru.app.database.UserDatabase;
import lv.javaguru.app.dependency_injection.DIComponent;
import lv.javaguru.app.dependency_injection.DIDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserShowSingleService {
	@Autowired
	private UserDatabase userDatabase;


	public UserShowSingleResponse execute (UserShowSingleRequest request) {

		User user = userDatabase.getUserById(request.getId());

		return new UserShowSingleResponse(user);

	}

}
