package lv.javaguru.app.core.response.admin;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.response.CodeError;
import lv.javaguru.app.core.response.Response;

import java.util.List;

public class ManageUserResponse extends Response {
	private User user;
	private String message;

	public ManageUserResponse (List<CodeError> errorList) {
		super(errorList);

	}

	public ManageUserResponse (String message) {
		this.message = message;
	}

	public ManageUserResponse (User user) {
		this.user = user;
	}

	public String getMessage () {
		return message;
	}

	public User getUser () {
		return user;
	}
}
