package lv.javaguru.app.core.response;


import lv.javaguru.app.core.domain.Person;

import java.util.List;

public class LogOutResponse extends Response {
	private String message;
	private Person currUser;

	public LogOutResponse (List<CodeError> errorList) {
		super(errorList);
	}


	public LogOutResponse (Person currUser, String message) {
		this.currUser = currUser;
		this.message = message;
	}

	public Person getCurrUser () {
		return currUser;
	}

	public String getMessage () {
		return message;
	}
}
