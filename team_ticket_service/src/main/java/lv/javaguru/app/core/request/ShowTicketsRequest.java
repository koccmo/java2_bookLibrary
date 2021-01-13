package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.Person;

public class ShowTicketsRequest {
	private final Person currUser;

	public ShowTicketsRequest (Person currUser) {
		this.currUser = currUser;
	}

	public Person getCurrUser () {
		return currUser;
	}
}
