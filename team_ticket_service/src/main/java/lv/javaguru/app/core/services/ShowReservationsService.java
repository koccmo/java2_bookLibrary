package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.request.ShowTicketsRequest;
import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.ShowTicketResponse;
import lv.javaguru.app.database.Database;

import java.util.ArrayList;
import java.util.List;

public class ShowReservationsService {

	private final Database database;

	public ShowReservationsService (Database database) {
		this.database = database;
	}


	public ShowTicketResponse<?> execute (ShowTicketsRequest request) {
		List<?> responseList = validate(request.getCurrUser());

		if (!responseList.isEmpty()) {
			return new ShowTicketResponse<>(responseList);
		}
		else if (database.getCurrentPerson() == request.getCurrUser() && request.getCurrUser().getPersonType() != PersonType.ADMIN)
			responseList = database.getAllUserTickets(request.getCurrUser());
		else {
			responseList = database.getAllTickets();
		}

		return new ShowTicketResponse<>(responseList);
	}

	private List<CodeError> validate (User user) {
		return new ArrayList<>();
	}
}
