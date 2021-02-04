package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.Flight;

import java.time.LocalDate;
import java.util.Date;

public class EditFlightValueRequest {
	private final Flight flight;
	private String newValue;
	private String[] newValues;
	private Date newDate;

	public EditFlightValueRequest (Flight flight, String newValue) {
		this.flight = flight;
		this.newValue = newValue;
	}

	public EditFlightValueRequest (Flight flight, String[] newValues) {
		this.flight = flight;
		this.newValues = newValues;
	}

	public EditFlightValueRequest (Flight flight, Date newDate) {
		this.flight = flight;
		this.newDate = newDate;
	}

	public Flight getFlight () {
		return flight;
	}

	public String getValue () {
		return newValue;
	}

	public Date getNewDate () {
		return newDate;
	}

	public String[] getNewValues () {
		return newValues;
	}
}
