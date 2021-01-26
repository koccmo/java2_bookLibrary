package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.request.FlightShowAllRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.FlightShowAllResponse;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.UserDatabase;
import lv.javaguru.app.dependency_injection.DIDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlightShowAllService {

	@Autowired
	private Database reservations;
	@Autowired
	private UserDatabase userDatabase;


	public FlightShowAllResponse<?> execute (FlightShowAllRequest request) {
		List<?> responseList = validate(request.getCurrUser());

		if (!responseList.isEmpty()) {
			return new FlightShowAllResponse<>(responseList);
		}
		else if (userDatabase.getCurrentUser() == request.getCurrUser() && request.getCurrUser().getPersonType() != PersonType.ADMIN)
			responseList = reservations.getAllUserFlights(request.getCurrUser());
		else {
			responseList = reservations.getAllFlights();
		}

		return new FlightShowAllResponse<>(responseList);
	}

	private List<CodeError> validate (User user) {
		List<CodeError> errorList = new ArrayList<>();

		if (!userDatabase.containsUser(user.getId()))
			errorList.add(new CodeError("User", "no user in database"));

		return errorList;
	}
}
