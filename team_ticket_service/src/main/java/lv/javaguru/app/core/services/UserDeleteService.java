package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.UserDeleteRequest;
import lv.javaguru.app.core.response.UserDeleteResponse;
import lv.javaguru.app.database.repository.AuthoritiesRepository;
import lv.javaguru.app.database.repository.FlightRepository;
import lv.javaguru.app.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class UserDeleteService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthoritiesRepository authoritiesRepository;

	@Autowired
	private FlightRepository flightRepository;

	public UserDeleteResponse execute (UserDeleteRequest request) {
		List<CodeError> errors = new ArrayList<>();

		if (!flightRepository.deleteFlightsByUserId(request.getId())) {
		//	errors.add(new CodeError("Id", "No flights are associated with such user ID"));
			//return new UserDeleteResponse(errors);
			System.out.println("No flights are associated with such user ID");
		}

		User userToDelete = userRepository.getUserById(request.getId());
		if (userToDelete == null) {
			errors.add(new CodeError("Id", "No user is associated with such user ID"));
			return new UserDeleteResponse(errors);
		}

		if (!authoritiesRepository.deleteRoleByUsername(userToDelete.getUsername())) {
		//	errors.add(new CodeError("Id", "No authorities are associated with such user ID"));
		//	return new UserDeleteResponse(errors);

			System.out.println("No authorities are associated with such user ID");

		}

		if (!userRepository.removeUser(request.getId())) {
			errors.add(new CodeError("Id", "No user with such user ID"));
			return new UserDeleteResponse(errors);
		}

		return new UserDeleteResponse(true);
	}

}
