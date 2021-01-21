package lv.javaguru.app.core.request.admin;

import lv.javaguru.app.core.domain.User;

public class EditUserRequest extends User {

	public EditUserRequest (Long id) {
		this.id = id;
	}

	private String newName;

	public EditUserRequest (Long id, String newName) {
		this.id = id;
		this.newName = newName;
	}


	public String getNewName () {
		return newName;
	}
}
