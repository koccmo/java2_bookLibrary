package lv.javaguru.app.core.response.admin;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.Response;

import java.util.List;

public class EditUserResponse extends Response {
	private User user;
	private String message;

	public EditUserResponse (List<CodeError> errorList) {
		super(errorList);

	}

	public EditUserResponse (String message) {
		this.message = message;
	}

	public EditUserResponse (User user) {
		this.user = user;
	}

	public String getMessage () {
		return message;
	}

	public User getUser () {
		return user;
	}
}
