package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.CodeError;

import java.util.List;

public class UserAddResponse extends Response {

	private String message;

	public UserAddResponse (String message) {
		this.message = message;
	}

	public UserAddResponse (List<CodeError> errorList) {
		super(errorList);
	}

	public String getMessage () {
		return message;
	}
}
