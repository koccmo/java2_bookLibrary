package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.User;

public class FlightShowAllRequest {
	private User user;



	public FlightShowAllRequest (User user) {
		this.user = user;
	}

	public User getUser () {
		return user;
	}

	public void setUser (User user) {
		this.user = user;
	}
}
