package lv.javaguru.app.core.request.admin;

import lv.javaguru.app.core.domain.User;

public class EditSurnameRequest {
	private final User user;
	private final String newSurname;

	public EditSurnameRequest (User user, String newSurname) {
		this.user = user;
		this.newSurname = newSurname;
	}

	public User getUser () {
		return user;
	}

	public String getNewSurname () {
		return newSurname;
	}
}
