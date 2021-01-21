package lv.javaguru.app.core.response.admin;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.Response;

import java.util.List;

public class EditUserNameResponse extends Response {
	private String message;

	public String getMessage () {
		return message;
	}

	public EditUserNameResponse (String message) {
		this.message = message;
	}

	public EditUserNameResponse (List<CodeError> errorList) {
		super(errorList);
	}
}
