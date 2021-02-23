package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.request.DeleteFlightRequest;
import lv.javaguru.app.core.request.DeleteTicketRequest;
import lv.javaguru.app.core.response.DeleteTicketResponse;
import lv.javaguru.app.core.response.FlightDeleteResponse;
import lv.javaguru.app.database.repository.AuthoritiesRepository;
import lv.javaguru.app.database.repository.FlightRepository;
import lv.javaguru.app.database.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class TicketDeleteService {

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private AuthoritiesRepository authoritiesRepository;

	public DeleteTicketResponse execute (DeleteTicketRequest request) {
		List<CodeError> errors = new ArrayList<>();

		//	if (!errors.isEmpty())
		//		return new FlightDeleteResponse(errors);

		if (!ticketRepository.deleteTicketById(request.getId())) {
			errors.add(new CodeError("Ticket", "Haven't managed to delete ticket with Id: " + request.getId()));
			return new DeleteTicketResponse(errors);
		}

		return new DeleteTicketResponse("ticket '" + request.getId() + "' was deleted!");
	}
/*

	private List<CodeError> validate (DeleteFlightRequest request) {
		List<CodeError> errors = new ArrayList<>();

		Flight flight = flightRepository.getFlightById(request.getId());
		if (flight == null) {
			errors.add(new CodeError("Id", "No flight with entered ID"));
		}

		if (!authoritiesRepository.getUserRoleByUsername(request.getUser().getUsername()).getAuthority().equals("ROLE_ADMIN"))
			if (!flightRepository.isUserFlight(request.getId(), request.getUser()))
				errors.add(new CodeError("Id", "User don't have flight with such ID!"));

		return errors;
	}
*/

}
