package lv.javaguru.app.core.request;


public class UserEditRequest {
	private final Long id;
	private String newValue;

	public UserEditRequest (Long id, String newValue) {
		this.id = id;
		this.newValue = newValue;
	}

	public UserEditRequest (Long id) {
		this.id = id;
	}

	public Long getId () {
		return id;
	}

	public String getNewValue () {
		return newValue;
	}

}
