package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.User;
import lv.javaguru.app.core.domain.CodeError;

import java.util.List;

public class UserEditResponse extends Response {

	private User user;
	private String message;

	public UserEditResponse (List<CodeError> errorList) {
		super(errorList);

	}

	public UserEditResponse (String message) {
		this.message = message;
	}

	public UserEditResponse (User user) {
		this.user = user;
	}

	public String getMessage () {
		return message;
	}

	public User getUser () {
		return user;
	}
}
