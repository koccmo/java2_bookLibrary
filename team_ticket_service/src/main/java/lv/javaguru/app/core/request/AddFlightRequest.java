package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.Flight;

public class AddFlightRequest {
	private final Flight Flight;

	public AddFlightRequest (Flight flight) {
		Flight = flight;
	}

	public Flight getFlight () {
		return Flight;
	}
}
