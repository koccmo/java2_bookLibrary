package lv.javaguru.app.core.services;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.request.FlightShowOneRequest;
import lv.javaguru.app.core.request.ShowTicketByIdRequest;
import lv.javaguru.app.core.response.FlightShowOneResponse;
import lv.javaguru.app.core.response.ShowTicketByIdResponse;
import lv.javaguru.app.database.repository.FlightRepository;
import lv.javaguru.app.database.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class ShowTicketByIdService {

	@Autowired
	private TicketRepository ticketRepository;


	public ShowTicketByIdResponse execute (ShowTicketByIdRequest request) {

		Ticket ticket = ticketRepository.getTicketById(request.getId());

		return new ShowTicketByIdResponse(ticket);
	}

	private List<CodeError> validate (User user) {
		return new ArrayList<>();
	}

}
