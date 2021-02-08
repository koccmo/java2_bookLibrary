package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.CodeError;

import java.util.List;

public class UserDeleteResponse extends Response {

	private CodeError error;
	private boolean isDeleted;
	private List<CodeError> errorList;

	public UserDeleteResponse (List<CodeError> errorList) {
		this.errorList = errorList;
	}

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
