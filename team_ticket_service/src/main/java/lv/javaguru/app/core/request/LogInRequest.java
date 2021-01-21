package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.User;

public class LogInRequest {

	private final User user;


	public LogInRequest (User user) {
		this.user = user;
	}

	public User getUser () {
		return user;
	}

}
