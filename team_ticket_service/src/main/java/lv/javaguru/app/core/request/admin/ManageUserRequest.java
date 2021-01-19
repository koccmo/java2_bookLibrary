package lv.javaguru.app.core.request.admin;

import lv.javaguru.app.core.domain.User;

public class ManageUserRequest {
	private final Long id;

	public ManageUserRequest (Long id) {
		this.id = id;
	}

	public Long getId () {
		return id;
	}
}
