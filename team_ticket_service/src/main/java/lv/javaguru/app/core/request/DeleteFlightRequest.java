package lv.javaguru.app.core.request;

import lv.javaguru.app.core.domain.User;

public class DeleteFlightRequest {

	private Long id;
	private User user;

	public DeleteFlightRequest () {
	}

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

	public void setId (Long id) {
		this.id = id;
	}

	public User getUser () {
		return user;
	}

	public void setUser (User user) {
		this.user = user;
	}
}
