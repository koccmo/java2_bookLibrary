package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.CodeError;

import java.util.List;

public abstract class Response  {

	private List<CodeError> errorList;

	public Response () {
	}

	public Response (List<CodeError> errorList) {
		this.errorList = errorList;
	}

	public List<CodeError> getErrorList () {
		return errorList;
	}

	public boolean hasErrors () {
		return errorList != null && !errorList.isEmpty();
	}


}
