package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserShowSingleRequest;
import lv.javaguru.app.core.response.UserShowSingleResponse;
import lv.javaguru.app.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class UserShowSingleService {

	@Autowired
	private UserRepository userRepository;

	public UserShowSingleResponse execute (UserShowSingleRequest request) {

		User user = userRepository.getUserById(request.getId());

		return new UserShowSingleResponse(user);

	}

}
