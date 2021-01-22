package lv.javaguru.app.core.request;


public class UserShowSingleRequest {
	private final Long id;

	public UserShowSingleRequest (Long id) {
		this.id = id;
	}

	public Long getId () {
		return id;
	}
}
