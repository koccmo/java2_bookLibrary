package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.User;

public class ShowTicketsRequest {
	private final User currUser;

	public ShowTicketsRequest (User currUser) {
		this.currUser = currUser;
	}

	public User getCurrUser () {
		return currUser;
	}
}
