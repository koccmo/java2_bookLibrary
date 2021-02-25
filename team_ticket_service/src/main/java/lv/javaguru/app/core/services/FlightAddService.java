package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.request.AddFlightRequest;
import lv.javaguru.app.core.services.validators.AddFlightRequestValidator;
import lv.javaguru.app.core.response.AddFlightResponse;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.database.repository.FlightRepository;
import lv.javaguru.app.database.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class FlightAddService {

	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private AddFlightRequestValidator validator;


	public AddFlightResponse execute (AddFlightRequest request) {
		List<CodeError> errors = validator.validate(request);

		if (!errors.isEmpty())
			return new AddFlightResponse(errors);

		Long id = ticketRepository.addTicket(request.getTicket());
		request.getTicket().setId(id);

		flightRepository.addFlight(new Flight(request.getUser(), request.getTicket()));

		return new AddFlightResponse();
	}
}
