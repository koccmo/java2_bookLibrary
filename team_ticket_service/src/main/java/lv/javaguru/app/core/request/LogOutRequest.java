package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.User;

public class LogOutRequest {
	private final User user;

	public LogOutRequest (User user) {
		this.user = user;
	}

	public User getPerson() {
		return user;
	}
}
