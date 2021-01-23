package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.Flight;

import java.time.LocalDate;

public class EditFlightValueRequest {
	private Flight flight;
	private String newValue;
	private LocalDate newDate;

	public EditFlightValueRequest (Flight flight, String newValue) {
		this.flight = flight;
		this.newValue = newValue;
	}

	public EditFlightValueRequest (Flight flight, LocalDate newDate) {
		this.flight = flight;
		this.newDate = newDate;
	}

	public Flight getFlight () {
		return flight;
	}

	public String getValue () {
		return newValue;
	}

	public LocalDate getNewDate () {
		return newDate;
	}
}
