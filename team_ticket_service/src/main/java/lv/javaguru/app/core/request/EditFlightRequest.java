package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.Ticket;

public class EditFlightRequest {

	private Long id;
	private Flight flight;
	private Ticket ticket;
	private String newValue;


	public EditFlightRequest (Long id) {
		this.id = id;
	}

	public EditFlightRequest (Ticket ticket, String newValue) {
		this.ticket = ticket;
		this.newValue = newValue;
	}

	public EditFlightRequest (Flight flight, String newValue) {
		this.newValue = newValue;
		this.flight = flight;
	}

	public Flight getFlight () {
		return flight;
	}

	public Long getFlightId () {
		return flight.getId();
	}

	public String getNewValue () {
		return newValue;
	}

	public Long getId () {
		return id;
	}

	public Ticket getTicket () {
		return ticket;
	}
}
