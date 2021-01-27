package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.Flight;

import java.util.List;

public class FlightEditResponse extends Response {

	private Flight flight;
	private String message;


	public FlightEditResponse (Flight flight) {
		this.flight = flight;
	}

	public FlightEditResponse (String message) {
		this.message = message;
	}

	public FlightEditResponse (List<CodeError> errorList) {
		super(errorList);
	}

	public Flight getFlight () {
		return flight;
	}

	public String getMessage () {
		return message;
	}
}
