package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.Person;
import lv.javaguru.app.core.domain.PersonType;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.request.ShowTicketsRequest;
import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.LogOutResponse;
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
		List<CodeError> errors = validate(request.getCurrUser());


		if (!errors.isEmpty())
			return new ShowTicketResponse<>(errors);

		else
			return new ShowTicketResponse<>(database.getAllTickets(request.getCurrUser()));

	}

	private List<CodeError> validate (Person user) {

		//if (database.containsPerson(user) && user.getName().equals("Mike"))
		return new ArrayList<>();
		//else
		//	return new ArrayList<>() {{
		//		add(new CodeError("", "fuck"));
		//	}};
	}
}
