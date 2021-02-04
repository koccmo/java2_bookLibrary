package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.request.UserDeleteRequest;
import lv.javaguru.app.core.response.UserDeleteResponse;
import lv.javaguru.app.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class UserDeleteService {

	@Autowired
	private UserRepository userRepository;


	public UserDeleteResponse execute (UserDeleteRequest request) {
		List<CodeError> errors = new ArrayList<>();

		if (!userRepository.removeUser(request.getId())) {
			errors.add(new CodeError("Id", "No user with such Id"));
			return new UserDeleteResponse(errors);
		}

		return new UserDeleteResponse(true);
	}

}
