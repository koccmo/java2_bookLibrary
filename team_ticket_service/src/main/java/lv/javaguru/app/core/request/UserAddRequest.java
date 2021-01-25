package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.User;

public class UserAddRequest {

	private final User user;

	public UserAddRequest (User user) {
		this.user = user;
	}

	public User getUser () {
		return user;
	}
}
