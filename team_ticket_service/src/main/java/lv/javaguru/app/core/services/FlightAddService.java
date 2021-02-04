package lv.javaguru.app.core.services;

import lv.javaguru.app.core.request.AddFlightRequest;
import lv.javaguru.app.core.services.validators.AddFlightRequestValidator;
import lv.javaguru.app.core.response.FlightAddResponse;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.database.repository.FlightRepository;
import lv.javaguru.app.database.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
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


	public FlightAddResponse execute (AddFlightRequest request) {
		List<CodeError> errors = validator.validate(request);

		if (!errors.isEmpty())
			return new FlightAddResponse(errors);

		Long id = ticketRepository.addTicket(request.getFlight().getTicket());
		request.getFlight().getTicket().setId(id);

		flightRepository.addFlight(request.getFlight());

		return new FlightAddResponse();
	}
}
