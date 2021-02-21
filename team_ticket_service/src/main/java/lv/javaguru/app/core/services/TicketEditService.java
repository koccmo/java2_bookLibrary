package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.request.EditFlightRequest;
import lv.javaguru.app.core.request.EditFlightValueRequest;
import lv.javaguru.app.core.request.ticket_update.UpdateTicketDestination;
import lv.javaguru.app.core.request.ticket_update.UpdateTicketOrigin;
import lv.javaguru.app.core.request.ticket_update.UpdateTicketSeat;
import lv.javaguru.app.core.response.FlightEditResponse;
import lv.javaguru.app.core.response.TicketUpdateResponse;
import lv.javaguru.app.core.services.validators.EditFlightRequestValidator;
import lv.javaguru.app.database.repository.FlightRepository;
import lv.javaguru.app.database.repository.TicketRepository;
import lv.javaguru.app.database.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Transactional
public class TicketEditService {

	@Autowired
	private TicketRepository ticketRepository;


	public TicketUpdateResponse execute (UpdateTicketOrigin request) {
		String originCountry = request.getTicketNewOriginCountry();
		String originCity = request.getTicketNewOriginCity();
		Long id = request.getId();

		List<CodeError> errors = new ArrayList<>();

		if (!ticketRepository.updateTicketOriginByTicketId(id, originCountry, originCity))
			errors.add(new CodeError("Ticket's origin", "Haven't managed to update ticket's origin!"));

		if (!errors.isEmpty())
			return new TicketUpdateResponse(errors);

		String message = "Ticket's origin was updated!";
		return new TicketUpdateResponse(message);
	}


	public TicketUpdateResponse execute (UpdateTicketDestination request) {
		String destinationCountry = request.getTicketNewDestinationCountry();
		String destinationCity = request.getTicketNewDestinationCity();
		Long id = request.getId();

		List<CodeError> errors = new ArrayList<>();

		if (!ticketRepository.updateTicketDestinationByTicketId(id, destinationCountry, destinationCity))
			errors.add(new CodeError("Ticket's destination", "Haven't managed to update ticket's destination!"));

		if (!errors.isEmpty())
			return new TicketUpdateResponse(errors);

		String message = "Ticket's destination was updated!";
		return new TicketUpdateResponse(message);
	}


	public TicketUpdateResponse execute (UpdateTicketSeat request) {
		String seat = request.getTicketNewSeat();
		Long id = request.getId();

		List<CodeError> errors = new ArrayList<>();

		if (!ticketRepository.updateTicketSeatByTicketId(id, seat))
			errors.add(new CodeError("Ticket's seat", "Haven't managed to update ticket's seat!"));

		if (!errors.isEmpty())
			return new TicketUpdateResponse(errors);

		String message = "Ticket's seat was updated!";
		return new TicketUpdateResponse(message);
	}

}
