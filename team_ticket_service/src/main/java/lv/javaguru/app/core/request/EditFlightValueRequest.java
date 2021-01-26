package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.Flight;

import java.time.LocalDate;

public class EditFlightValueRequest {
	private final Flight flight;
	private String newValue;
	private String[] newValues;
	private LocalDate newDate;

	public EditFlightValueRequest (Flight flight, String newValue) {
		this.flight = flight;
		this.newValue = newValue;
	}

	public EditFlightValueRequest (Flight flight, String[] newValues) {
		this.flight = flight;
		this.newValues = newValues;
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

	public String[] getNewValues () {
		return newValues;
	}
}
