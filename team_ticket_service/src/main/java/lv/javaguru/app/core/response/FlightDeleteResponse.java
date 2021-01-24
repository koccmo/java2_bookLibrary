package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.CodeError;

import java.util.List;

public class FlightDeleteResponse extends Response {

	private String message;

	public FlightDeleteResponse (String message) {
		this.message = message;
	}

	public FlightDeleteResponse (List<CodeError> errorList) {
		super(errorList);
	}

	public String getMessage () {
		return message;
	}
}
