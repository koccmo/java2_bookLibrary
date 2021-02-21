package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.User;

public class LogOutRequest {
	private User user;

	public LogOutRequest () {
	}

	public LogOutRequest (User user) {
		this.user = user;
	}

	public User getUser () {
		return user;
	}

	public void setUser (User user) {
		this.user = user;
	}
}
