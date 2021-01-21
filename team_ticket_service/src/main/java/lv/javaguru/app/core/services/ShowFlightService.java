package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.request.ShowFlightRequest;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.ShowFlightResponse;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class ShowFlightService {

	private final Database reservations;
	private final UserDatabase userDatabase;

	public ShowFlightService (UserDatabase userDatabase, Database reservations) {
		this.userDatabase = userDatabase;
		this.reservations = reservations;
	}


	public ShowFlightResponse<?> execute (ShowFlightRequest request) {
		List<?> responseList = validate(request.getCurrUser());

		if (!responseList.isEmpty()) {
			return new ShowFlightResponse<>(responseList);
		}
		else if (userDatabase.getCurrentUser() == request.getCurrUser() && request.getCurrUser().getPersonType() != PersonType.ADMIN)
			responseList = reservations.getAllUserReservations(request.getCurrUser());
		else {
			responseList = reservations.getAllReservations();
		}

		return new ShowFlightResponse<>(responseList);
	}

	private List<CodeError> validate (User user) {
		return new ArrayList<>();
	}
}
