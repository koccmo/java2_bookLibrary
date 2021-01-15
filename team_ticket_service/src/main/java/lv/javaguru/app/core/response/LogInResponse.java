package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.Person;

import java.util.List;

public class LogInResponse extends Response {
	private Person user;

	public LogInResponse (List<CodeError> errorList) {
		super(errorList);
	}

	public LogInResponse (Person user) {
		this.user = user;
	}

	public Person getUser () {
		return user;
	}
}
