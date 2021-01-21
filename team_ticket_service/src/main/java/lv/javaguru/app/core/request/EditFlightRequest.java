package lv.javaguru.app.core.request;


import lv.javaguru.app.core.domain.Flight;

public class EditFlightRequest extends Flight {

	private Long id;
	private Flight flight;
	private String newValue;

	public EditFlightRequest (Flight flight, String newValue) {
		this.newValue = newValue;
		this.flight = flight;
	}

	public EditFlightRequest (Long id) {
		this.id = id;
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
}
