package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.Ticket;
import lv.javaguru.app.core.domain.User;

public class EditFlightRequest {

	private Long id;
	private Flight flight;
	private String newValue;
	private User user;
	private Ticket ticket;

	public User getUser () {
		return user;
	}

	public EditFlightRequest (Long id, Ticket ticket) {
		this.id = id;
		this.ticket = ticket;
	}

	public EditFlightRequest (Long id) {
		this.id = id;
	}

	public EditFlightRequest (Long id, User user) {
		this.id = id;
		this.user = user;

	}

	public EditFlightRequest (Flight flight, String newValue) {
		this.newValue = newValue;
		this.flight = flight;
	}

	public Long getId () {
		return id;
	}

	public Flight getFlight () {
		return flight;
	}

	public String getNewValue () {
		return newValue;
	}

}
