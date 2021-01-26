package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.Flight;


public class FlightShowOneResponse {

	private final Flight flight;

	public FlightShowOneResponse (Flight flight) {
		this.flight = flight;
	}

	public Flight getFlight () {
		return flight;
	}
}
