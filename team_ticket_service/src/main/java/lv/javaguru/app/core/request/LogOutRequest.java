package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.Person;

public class LogOutRequest {
	private final Person person;

	public LogOutRequest (Person person) {
		this.person = person;
	}

	public Person getPerson() {
		return person;
	}
}
