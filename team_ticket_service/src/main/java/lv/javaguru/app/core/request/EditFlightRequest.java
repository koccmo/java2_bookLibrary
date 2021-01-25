package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.Flight;

public class EditFlightRequest {

	private Long requestId;
	private Flight flight;
	private String newValue;


	public EditFlightRequest (Long requestId) {
		this.requestId = requestId;
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
