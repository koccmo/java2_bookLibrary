package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.Role;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.FlightShowAllRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.FlightShowAllResponse;
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
public class FlightShowAllService {

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthoritiesRepository authoritiesRepository;


	public FlightShowAllResponse execute (FlightShowAllRequest request) {
		List<?> responseList = validate(request.getUser());

		if (!responseList.isEmpty()) {
			return new FlightShowAllResponse(responseList.toString());
		}

		Role userRole = authoritiesRepository.getUserRoleByUsername(request.getUser().getUsername());

		if (userRole.getAuthority().equals("ROLE_ADMIN"))
			responseList = flightRepository.getAllFlights();

		if (userRole.getAuthority().equals("ROLE_USER"))
			responseList = flightRepository.getAllUserFlights(request.getUser());

		return new FlightShowAllResponse(responseList.toString());
	}

	//public FlightShowAllResponse<?> execute () {
	//	return new FlightShowAllResponse<>(flightRepository.getAllFlights());
	//}

	private List<CodeError> validate (User user) {
		List<CodeError> errorList = new ArrayList<>();

		//if (!database.userTableContainsUser(user.getId()))
		//	errorList.add(new CodeError("User", "no user in database"));

		return errorList;
	}
}
