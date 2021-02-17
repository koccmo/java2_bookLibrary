package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.FlightShowAllRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.FlightShowAllResponse;
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
	private UserRepository userRepository;


	public FlightShowAllResponse<?> execute (FlightShowAllRequest request) {
		List<?> responseList = validate(request.getUser());

		if (!responseList.isEmpty()) {
			return new FlightShowAllResponse<>(responseList);
		}
		//else if (request.getUser().getRoles().stream().findFirst() != Role.ADMIN)
		//	responseList = flightRepository.getAllUserFlights(request.getUser());
		else {
			responseList = flightRepository.getAllFlights();
		}

		return new FlightShowAllResponse<>(responseList);
	}

	public FlightShowAllResponse<?> execute () {
		return new FlightShowAllResponse<>(flightRepository.getAllFlights());
	}

	private List<CodeError> validate (User user) {
		List<CodeError> errorList = new ArrayList<>();

		//if (!database.userTableContainsUser(user.getId()))
		//	errorList.add(new CodeError("User", "no user in database"));

		return errorList;
	}
}
