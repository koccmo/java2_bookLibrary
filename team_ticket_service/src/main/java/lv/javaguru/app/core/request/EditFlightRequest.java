package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.Flight;
import lv.javaguru.app.core.domain.User;

public class EditFlightRequest {

	private Long requestId;
	private Flight flight;
	private String newValue;
	private User user;

	public User getUser () {
		return user;
	}

	public EditFlightRequest (Long requestId) {
		this.requestId = requestId;
	}
	public EditFlightRequest (Long requestId, User user) {
		this.requestId = requestId;
		this.user = user;

	}

	public EditFlightRequest (Flight flight, String newValue) {
		this.newValue = newValue;
		this.flight = flight;
	}

	public Long getRequestId () {
		return requestId;
	}

	public Flight getFlight () {
		return flight;
	}

	public String getNewValue () {
		return newValue;
	}

}
