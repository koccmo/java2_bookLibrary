package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.User;

public class UserShowAllRequest {
	private User user;
	private Long id;

	public UserShowAllRequest () {
	}

	public UserShowAllRequest (Long id) {
		this.id = id;
	}

	public UserShowAllRequest (User user) {
		this.user = user;
	}

	public User getUser () {
		return user;
	}

	public Long getId () {
		return id;
	}
}
