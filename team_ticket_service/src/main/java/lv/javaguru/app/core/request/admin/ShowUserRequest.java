package lv.javaguru.app.core.request.admin;

import lv.javaguru.app.core.domain.User;

public class ShowUserRequest {
	private final User admin;

	public ShowUserRequest (User admin) {
		this.admin = admin;
	}

	public User getAdmin () {
		return admin;
	}
}
