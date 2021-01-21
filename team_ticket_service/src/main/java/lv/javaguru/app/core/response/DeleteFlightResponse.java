package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.CodeError;

import java.util.List;

public class DeleteFlightResponse extends Response {

	private String message;

	public DeleteFlightResponse (String message) {
		this.message = message;
	}

	public DeleteFlightResponse (List<CodeError> errorList) {
		super(errorList);
	}

	public String getMessage () {
		return message;
	}
}
