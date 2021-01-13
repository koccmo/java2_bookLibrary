package lv.javaguru.app.core.response;

import java.util.List;

public class DeleteReservationResponse extends Response {

	private String message;

	public DeleteReservationResponse (String message) {
		this.message = message;
	}

	public DeleteReservationResponse (List<CodeError> errorList) {
		super(errorList);
	}

	public String getMessage () {
		return message;
	}
}
