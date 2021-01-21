package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.User;

public class ShowFlightRequest {
	private final User currUser;

	public ShowFlightRequest (User currUser) {
		this.currUser = currUser;
	}

	public User getCurrUser () {
		return currUser;
	}
}
