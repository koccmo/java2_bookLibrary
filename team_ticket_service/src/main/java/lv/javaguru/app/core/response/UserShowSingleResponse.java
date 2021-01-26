package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.User;


public class UserShowSingleResponse {

	private final User user;

	public UserShowSingleResponse (User user) {
		this.user = user;
	}

	public User getUser () {
		return user;
	}
}
