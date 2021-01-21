package lv.javaguru.app.core.request.admin;

import lv.javaguru.app.core.domain.User;

public class EditUserNameRequest {
	private final User user;
	private final String newName;

	public EditUserNameRequest (User user, String newName) {
		this.user = user;
		this.newName = newName;
	}

	public User getUser () {
		return user;
	}

	public String getNewName () {
		return newName;
	}
}
