package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.domain.User;

import java.util.List;

public class LogOutResponse extends Response {

	private String message;
	private User currUser;

	public LogOutResponse (List<CodeError> errorList) {
		super(errorList);
	}


	public LogOutResponse (User currUser, String message) {
		this.currUser = currUser;
		this.message = message;
	}

	public User getCurrUser () {
		return currUser;
	}

	public String getMessage () {
		return message;
	}
}
