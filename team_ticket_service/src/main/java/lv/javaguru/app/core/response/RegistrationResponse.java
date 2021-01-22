package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.CodeError;

import java.util.List;

public class RegistrationResponse extends Response {
	private String message;

	public RegistrationResponse (String message) {
		this.message = message;
	}

	public RegistrationResponse (List<CodeError> errorList) {
		super(errorList);
	}

	public String getMessage () {
		return message;
	}
}
