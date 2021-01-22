package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.CodeError;

public class UserDeleteResponse {
	private CodeError error;
	private boolean isDeleted;

	public UserDeleteResponse (CodeError error) {
		this.error = error;
	}

	public UserDeleteResponse (boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public CodeError getError () {
		return error;
	}

	public boolean hasError () {
		return error != null;
	}

	public boolean isDeleted () {
		return isDeleted;
	}
}
