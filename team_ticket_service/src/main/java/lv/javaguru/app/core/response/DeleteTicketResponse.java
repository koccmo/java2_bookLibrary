package lv.javaguru.app.core.response;

import lv.javaguru.app.core.domain.CodeError;

import java.util.List;

public class DeleteTicketResponse extends Response {

	private String message;

	public DeleteTicketResponse (String message) {
		this.message = message;
	}

	public DeleteTicketResponse (List<CodeError> errorList) {
		super(errorList);
	}

	public String getMessage () {
		return message;
	}
}
