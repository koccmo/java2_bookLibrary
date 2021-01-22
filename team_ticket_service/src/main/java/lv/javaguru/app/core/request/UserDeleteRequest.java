package lv.javaguru.app.core.request;

public class UserDeleteRequest {
	private final Long id;

	public UserDeleteRequest (Long id) {
		this.id = id;
	}

	public Long getId () {
		return id;
	}
}
