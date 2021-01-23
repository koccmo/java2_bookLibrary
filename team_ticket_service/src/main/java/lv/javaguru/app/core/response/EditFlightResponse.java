package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.Flight;

import java.util.List;

public class EditFlightResponse extends Response {
	private Flight flight;
	private String message;


	public EditFlightResponse (Flight flight) {
		this.flight = flight;
	}

	public EditFlightResponse (String message) {
		this.message = message;
	}

	public EditFlightResponse (List<CodeError> errorList) {
		super(errorList);
	}

	public Flight getFlight () {
		return flight;
	}

	public String getMessage () {
		return message;
	}
}
