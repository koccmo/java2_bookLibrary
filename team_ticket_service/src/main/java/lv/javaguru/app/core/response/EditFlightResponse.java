package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.Flight;

import java.util.List;

public class EditFlightResponse extends Response {

	private Long id;
	private Flight flight;
	private String message;

	public EditFlightResponse (Long id) {
		this.id = id;
	}

	public EditFlightResponse (Flight flight) {
		this.flight = flight;
	}

	public EditFlightResponse (String message) {
		this.message = message;
	}

	public EditFlightResponse (List<CodeError> errorList) {
		super(errorList);
	}

	public Long getId () {
		return id;
	}

	public Flight getFlight () {
		return flight;
	}

	public String getMessage () {
		return message;
	}
}
