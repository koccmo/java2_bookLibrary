package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.User;

public class FlightShowAllRequest {
	private final User currUser;

	public FlightShowAllRequest (User currUser) {
		this.currUser = currUser;
	}

	public User getCurrUser () {
		return currUser;
	}
}
