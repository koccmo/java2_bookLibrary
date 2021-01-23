package lv.javaguru.app.core.request.edit;

import lv.javaguru.app.core.domain.Ticket;

import java.time.LocalDate;

public class EditTicketDepartureDateRequest {
	private LocalDate departureDate;
	private Long id;
	private Ticket ticket;

	public EditTicketDepartureDateRequest (Ticket ticket, LocalDate departureDate) {
		this.ticket = ticket;
	}

	public EditTicketDepartureDateRequest (Long id, LocalDate departureDate) {
		this.id = id;
		this.departureDate = departureDate;
	}

	public LocalDate getDepartureDate () {
		return departureDate;
	}

	public Long getId () {
		return id;
	}

	public Ticket getTicket () {
		return ticket;
	}
}