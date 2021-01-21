package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.request.ShowTicketsRequest;
import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.ShowTicketResponse;
import lv.javaguru.app.database.Database;
import lv.javaguru.app.database.UserDatabase;

import java.util.ArrayList;
import java.util.List;

public class ShowReservationsService {

	private final Database reservations;
	private final UserDatabase userDatabase;

	public ShowReservationsService (UserDatabase userDatabase, Database reservations) {
		this.userDatabase = userDatabase;
		this.reservations = reservations;
	}


	public ShowTicketResponse<?> execute (ShowTicketsRequest request) {
		List<?> responseList = validate(request.getCurrUser());

		if (!responseList.isEmpty()) {
			return new ShowTicketResponse<>(responseList);
		}
		else if (userDatabase.getCurrentUser() == request.getCurrUser() && request.getCurrUser().getPersonType() != PersonType.ADMIN)
			responseList = reservations.getAllUserReservations(request.getCurrUser());
		else {
			responseList = reservations.getAllReservations();
		}

		return new ShowTicketResponse<>(responseList);
	}

	private List<CodeError> validate (User user) {
		return new ArrayList<>();
	}
}
