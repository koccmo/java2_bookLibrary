package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.User;

public class DeleteFlightRequest {

	private final Long id;
	private User user;

	public DeleteFlightRequest (Long id) {
		this.id = id;
	}

	public DeleteFlightRequest (Long id, User user) {
		this(id);
		this.user = user;
	}

	public Long getId () {
		return id;
	}

	public User getUser () {
		return user;
	}
}
