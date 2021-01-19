package lv.javaguru.app.core.response.admin;

import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.Response;

import java.util.List;

public class EditUserSurnameResponse extends Response {
	private String message;

	public String getMessage () {
		return message;
	}

	public EditUserSurnameResponse (String message) {
		this.message = message;
	}

	public EditUserSurnameResponse (List<CodeError> errorList) {
		super(errorList);
	}
}
