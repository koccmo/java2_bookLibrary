package lv.javaguru.app.core.response.edit;

import lv.javaguru.app.core.domain.CodeError;
import lv.javaguru.app.core.response.Response;

import java.util.List;

public class EditTicketSeatResponse extends Response {
	private String message;

	public EditTicketSeatResponse (String message) {
		this.message = message;
	}

	public EditTicketSeatResponse (List<CodeError> errorList) {
		super(errorList);
	}

	public String getMessage () {
		return message;
	}
}
